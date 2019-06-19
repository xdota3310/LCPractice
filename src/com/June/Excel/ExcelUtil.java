package com.June.Excel;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel映射相应实体
 *
 * @author shijie.xu
 * @since 2019年06月18日
 */
public class ExcelUtil {

    /**
     * 2007+ 版本的excel
     */
    private final static String EXCEL2007U = ".xlsx";
    /**
     * 2003- 版本的excel
     */
    private final static String EXCEL2003L = ".xls";

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Sheet sheet;

    private Workbook workbook;

    private List<String> head = new ArrayList<>();

    private List<Object> resList = new ArrayList<>();

    private int coloumNum;

    private int rowNum;

    private void initWorkbook(MultipartFile file) throws Exception {
        this.workbook = chooseWorkbook(file);
    }

    private void initSheet(String sheetName) {
        this.sheet = workbook.getSheet(sheetName);

    }

    private void initHead() {
        if(this.sheet != null) {
            Row row = sheet.getRow(0);
//            for(int i = row.getFirstCellNum(); i <= row.getPhysicalNumberOfCells(); i++) {
            int i = 0;
            while(row.getCell(i) != null && !"".equals(row.getCell(i).toString().trim())) {
                head.add(row.getCell(i).toString().trim());
                ++i;
            }
//            }
        }
    }

    public List<Object> doImport(MultipartFile file, String sheetName, Object obj) throws Exception {
        initWorkbook(file);
        initSheet(sheetName);
        initHead();
        return resList;
    }

    public List<Object> doImport(MultipartFile file, String sheetName, Object obj, int start, int size) throws Exception {
        int end;
        if(size <= 0) {
            return resList;
        }
        end = start + size - 1;
        start = (start > 1) ? start : 1;
        initHead();
        resList = this.importBaseExcel(obj, start, end);
        return resList;
    }

    public int getTotal(MultipartFile file, String sheetName) throws Exception {
        initWorkbook(file);
        initSheet(sheetName);
//        Row row = sheet.getRow(0);
        return sheet.getLastRowNum() + 1;
    }

    /**
     * 获取实体对象返回属性名称
     *
     * @param obj 实体对象
     * @return
     * @throws Exception
     */
    public Field[] findEntityAllTypeName(Object obj) throws Exception {

        Class<? extends Object> cls = obj.getClass();

        return cls.getDeclaredFields();
    }

    /**
     * 根据文件选择excel版本
     *
     * @return
     * @throws Exception
     */
    public Workbook chooseWorkbook(MultipartFile file) throws Exception {

        Workbook workbook = null;

        //把MultipartFile转化为File
        CommonsMultipartFile cmf = (CommonsMultipartFile) file;
        DiskFileItem dfi = (DiskFileItem) cmf.getFileItem();
        File fo = dfi.getStoreLocation();

        String filename = file.getOriginalFilename();
        String fileType = (filename.substring(filename.lastIndexOf("."), filename.length())).toLowerCase();

        if(EXCEL2003L.equals(fileType)) {
            //2003-
            workbook = new HSSFWorkbook(FileUtils.openInputStream(fo));
        } else if(EXCEL2007U.equals(fileType)) {
            //2007+
            workbook = new XSSFWorkbook(FileUtils.openInputStream(fo));
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return workbook;
    }


    /**
     * 公共的导入excel方法
     *
     * @param obj 实体类
     * @return
     * @throws IOException
     */
    private List<Object> importBaseExcel(Object obj, int start, int end) throws IOException {
        try {
            //获取该实体所有定义的属性 返回Field数组
            Field[] entityName = this.findEntityAllTypeName(obj);
//            String classname = obj.getClass().getName();
            Class<?> clazz = obj.getClass();

            List<Object> list = new ArrayList<Object>();

            //循环插入数据
            for(int i = start; i <= end; i++) {

                Row row = sheet.getRow(i);

                //可以根据该类名生成Java对象
                Object pojo = clazz.newInstance();
                for(int j = 0; j < head.size(); j++) {
                    String value = head.get(j);
                    Field field = null;
                    Class tempClass = clazz;
                    while(tempClass != null && field == null) {
                        field = getField(tempClass, value);
                        tempClass = tempClass.getSuperclass();
                    }

                    field.setAccessible(true);
                    String type = field.getGenericType().toString();

                    Cell pname = row.getCell(j);
                    switch(type) {
                        case "char":
                        case "java.lang.Character":
                        case "class java.lang.String":
                            field.set(pojo, getVal(pname));
                            break;
                        case "int":
                        case "class java.lang.Integer":
                            field.set(pojo, Integer.valueOf(getVal(pname)));
                            break;
                        case "class java.util.Date":
                            field.set(pojo, dateFormat.format(pname.toString()));
                            break;
                        case "float":
                        case "double":
                        case "java.lang.Double":
                        case "java.lang.Float":
                        case "java.lang.Long":
                        case "java.lang.Short":
                        case "java.math.BigDecimal":
                            field.set(pojo, Double.valueOf(getVal(pname)));
                            break;
                        default:
                            break;
                    }
                }

                list.add(pojo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            workbook.close();
        }
        return null;
    }

    private Field getField(Class<?> cla, String name) {
        Field field = null;
        try {
            field = cla.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return field;
        }
        return field;

    }

    /**
     * 处理类型
     *
     * @param cell
     * @return
     */
    public static String getVal(Cell cell) {
        if(null != cell) {

            switch(cell.getCellType()) {
                // 数字
                case XSSFCell.CELL_TYPE_NUMERIC:

                    String val = cell.getNumericCellValue() + "";
                    int index = val.indexOf(".");

                    if(Integer.valueOf(val.substring(index + 1)) == 0) {
                        //处理科学计数法
                        DecimalFormat df = new DecimalFormat("0");
                        return df.format(cell.getNumericCellValue());
                    }
                    //double
                    return cell.getNumericCellValue() + "";
                // 字符串
                case XSSFCell.CELL_TYPE_STRING:
                    return cell.getStringCellValue() + "";
                // Boolean
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue() + "";
                // 公式
                case XSSFCell.CELL_TYPE_FORMULA:

                    try {
                        if(HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            return String.valueOf(date.getTime());
                        } else {
                            return String.valueOf((int) cell.getNumericCellValue());
                        }
                    } catch (IllegalStateException e) {
                        return String.valueOf(cell.getRichStringCellValue());
                    }
                    // 空值
                case XSSFCell.CELL_TYPE_BLANK:
                    return "";
                // 故障
                case XSSFCell.CELL_TYPE_ERROR:
                    return "";
                default:
                    return "未知类型   ";
            }
        } else {
            return "";
        }
    }

}
