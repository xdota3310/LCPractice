package com.company.P2018_10_31.pra;

import java.util.ArrayList;
import java.util.List;

/**
 * 722. Remove Comments
 *
 * @author shijie.xu
 * @since 2018年10月31日
 */
public class test {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder newStr = new StringBuilder();
        boolean is = false;
        for(int i = 0; i < source.length; i++) {
            if(!is) {
                newStr = new StringBuilder();
            }
            for(int j = 0; j < source[i].length(); j++) {
                if(!is && j + 1 < source[i].length() && source[i].charAt(j) == '/' && source[i].charAt(j + 1) == '/') {
                    break;
                } else if(!is && j + 1 < source[i].length() && source[i].charAt(j) == '/' && source[i].charAt(j + 1) == '*') {
                    is = true;
                } else if(is && j + 1 < source[i].length() && source[i].charAt(j) == '*' && source[i].charAt(j + 1) == '/') {
                    is = false;
                    j++;
                } else if(!is) {
                    newStr.append(source[i].charAt(j));
                }
            }
            if(!is && newStr.length() > 0) {
                res.add(newStr.toString());
            }
        }
        return res;
    }




    public static void main(String[] args) {
        String[] source = {"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
//        String[] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a April", "   " + "multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println(new test().removeComments(source));
    }
}
