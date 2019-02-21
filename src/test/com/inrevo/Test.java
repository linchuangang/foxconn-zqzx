package com.inrevo;

import com.inrevo.util.HttpUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {

            Path path = Paths.get("E:\\person.txt");
            List<String> strings = Files.readAllLines(path);
            for(String s:strings){
                if(!s.isEmpty()){
                    String url="http://localhost:8080/fsk/add?"+s;
                    System.out.println(url);
                    HttpUtils.sendHttpGet(url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void transfor() {
        try {
            String s = "北京";
            s = new String(s.getBytes("UTF-8"), "ISO-8859-1");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
