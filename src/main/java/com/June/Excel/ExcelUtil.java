package com.June.Excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * excel映射相应实体
 *
 * @author shijie.xu
 * @since 2019年06月18日
 */
public class ExcelUtil {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);


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

    private Object object;


    /**
     * 标识next的索引
     */
    private int index = 1;

    private int coloumNum;

    /**
     * 最后一行的索引
     */
    private int rowNum = 0;

    private void initWorkbook(File file) throws Exception {
        this.workbook = chooseWorkbook(file);
    }

    private void initSheet(String sheetName) {
        this.sheet = workbook.getSheet(sheetName);

    }

    /**
     * 初始化
     *
     * @param file
     * @param sheetName
     * @throws Exception
     */
    public void init(File file, String sheetName, Object obj) throws Exception {
        initWorkbook(file);
        initSheet(sheetName);
        object = obj;
        if(this.sheet != null && head.size() <= 0) {
            Row row = sheet.getRow(0);
            int i = 0;
            while(row.getCell(i) != null && !"".equals(row.getCell(i).toString().trim())) {
                head.add(row.getCell(i).toString().trim());
                ++i;
            }
            rowNum = sheet.getLastRowNum();
        }

    }

    /**
     * 全量转换
     *
     * @param file
     * @param sheetName
     * @param obj
     * @return
     * @throws Exception
     */
    @Deprecated
    public List<Object> doImport(File file, String sheetName, Object obj) throws Exception {
//        init(file, sheetName);

        return null;
    }

    public boolean hasNext() {
        if(index <= rowNum) {
            return true;
        }
        return false;
    }

    /**
     * 分批转换(需要先调用getTotal方法)
     *
     * @param size
     * @return
     * @throws Exception
     */
    public List<Object> next(int size) throws Exception {
        List<Object> resList = new ArrayList<>();
        if(size <= 0) {
            return resList;
        }
        int end = index + size - 1;
        resList = this.importBaseExcel(object, index, ((end <= rowNum) ? end : rowNum));
        index = index + size;
        return resList;
    }

    /**
     * 获取表单的行数
     *
     * @param file
     * @param sheetName
     * @return
     * @throws Exception
     */
    public int getTotal(File file, String sheetName, Object obj) throws Exception {
        init(file, sheetName, obj);
        return sheet.getLastRowNum() + 1;
    }

    /**
     * 获取实体对象返回属性名称
     *
     * @param obj 实体对象
     * @return
     * @throws Exception
     */
    private java.lang.reflect.Field[] findEntityAllTypeName(Object obj) throws Exception {

        Class<? extends Object> cls = obj.getClass();

        return cls.getDeclaredFields();
    }

    /**
     * 根据文件选择excel版本
     *
     * @return
     * @throws Exception
     */
    private Workbook chooseWorkbook(File file) throws Exception {

        Workbook workbook = null;

        //把File转化为File
//        CommonsFile cmf = (CommonsFile) file;
//        DiskFileItem dfi = (DiskFileItem) cmf.getFileItem();
//        File file = dfi.getStoreLocation();

        String filename = file.getName();
        String fileType = (filename.substring(filename.lastIndexOf("."), filename.length())).toLowerCase();

        if(EXCEL2003L.equals(fileType)) {
            //2003-
            workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
        } else if(EXCEL2007U.equals(fileType)) {
            //2007+

            workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
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
        List<Object> list = new ArrayList<Object>();
        Field field;
        Cell pname = null;
        String value = null;
        Class<?> clazz = obj.getClass();

        //循环插入数据
        for(int i = start; i <= end; i++) {
            Object pojo = null;
            Row row = null;
            try {
                row = sheet.getRow(i);
                pojo = clazz.newInstance();
            } catch (Exception e) {
//                LOGGER.error("ExcelUtil:" + e.toString());
            }
            for(int j = 0; j < head.size(); j++) {
                try {
                    value = head.get(j);
                    field = null;
                    Class tempClass = clazz;
                    while(tempClass != null && field == null) {
                        field = getField(tempClass, value);
                        tempClass = tempClass.getSuperclass();
                    }

                    field.setAccessible(true);
                    String type = field.getGenericType().toString();

                    pname = row.getCell(j);
                    switch(type) {
                        case "char":
                        case "java.lang.Character":
                        case "class java.lang.String":
                            if(pname != null) {
                                field.set(pojo, getVal(pname));
                            }
                            break;
                        case "int":
                        case "class java.lang.Integer":
                            if(pname != null) {
                                field.set(pojo, getVal(pname));
                            }
                            break;
                        case "class java.util.Date":
                            if(pname != null) {
                                field.set(pojo, getVal(pname));
                            }
                            break;
                        case "float":
                        case "double":
                        case "java.lang.Double":
                        case "java.lang.Float":
                        case "java.lang.Long":
                        case "java.lang.Short":
                        case "java.math.BigDecimal":
                            if(pname != null) {
                                field.set(pojo, getVal(pname));
                            }
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
//                    LOGGER.error("ExcelUtil:【" + (i + 1) + "行】【" + (j + 1) + "列】【" + value + "】 " + e.toString());
                }
            }
            list.add(pojo);
        }
        return list;
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
    public static Object getVal(Cell cell) {
        if(null != cell) {
            CellType cellType = cell.getCellType();

            if(cellType.equals(CellType.NUMERIC)) {
                if(DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            } else if(cellType.equals(CellType.STRING)) {
                return cell.getStringCellValue() + "";
            } else if(cellType.equals(CellType.BOOLEAN)) {
                return cell.getBooleanCellValue();
            } else if(cellType.equals(CellType.FORMULA)) {
                try {
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        return date.getTime();
                    } else {
                        return cell.getNumericCellValue();
                    }
                } catch (IllegalStateException e) {
                    return String.valueOf(cell.getRichStringCellValue());
                }
            } else if(cellType.equals(CellType.BLANK)) {
                return "";
            } else if(cellType.equals(CellType.ERROR)) {
                return "";
            } else {
                return "未知类型  ";
            }
        } else {
            return "";
        }
    }

    class Producer implements Runnable {

        protected BlockingQueue<Object> queue;

        Producer(BlockingQueue<Object> theQueue) {
            this.queue = theQueue;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    Object justProduced = getResource();
                    queue.put(justProduced);
                    System.out.println("生产者资源队列大小= " + queue.size());
                }
            } catch (InterruptedException ex) {
                System.out.println("生产者 中断");
            }
        }

        Object getResource() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("生产者 读 中断");
            }
            return new Object();
        }
    }

    public class Consumer implements Runnable {
        protected BlockingQueue<Object> queue;

        public Consumer(BlockingQueue<Object> theQueue) {
            this.queue = theQueue;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    Object obj = queue.take();
//                    LOGGER.error("消费者 资源 队列大小 " + queue.size());
//                    System.out.println("消费者 资源 队列大小 " + queue.size());
                }
            } catch (InterruptedException ex) {
                System.out.println("消费者 中断");
            }
        }

        void take(Object obj) {
            try {
                Thread.sleep(100); // simulate time passing
            } catch (InterruptedException ex) {
                System.out.println("消费者 读 中断");
            }
            System.out.println("消费对象 " + obj);
        }
    }
}
