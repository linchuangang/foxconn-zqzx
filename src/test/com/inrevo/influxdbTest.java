package com.inrevo;



import com.inrevo.util.HttpUtils;
import com.inrevo.util.ReadUtil;
import org.junit.Test;

import java.io.File;

public class influxdbTest {

    /**
     * insert disk_free,hostname=server01 value=442221834240i 1435362189575692182
     * InfluxDB的insert中，表名与数据之间用逗号（,）分隔，tag和field之间用 空格分隔，多个tag或者多个field之间用逗号（,）分隔
     */

    @Test
    public void testInsert(){
        String url="http://192.168.0.129:8086/write?db=fsk";
        //        model time area tag1 tag2 tag3 tag4 filed1 filed2 filed3 filed4
        String result= HttpUtils.sendHttpPost(url,"fsk,model=test,area=1,tag1=one,tag2=two,tag3=three,tag4=4 filed1=1,filed2=2,filed3=3,filed4=4 1434055562000000000");
        System.out.println(result);
    }

    @Test
    public void testInsertFile(){
        System.out.println(new File(".").getAbsolutePath());
        String url="http://192.168.0.129:8086/write?db=test";
        //        cpu_load_short,host=server01,region=us-west value=0.64 1434055562000000000
        String result=HttpUtils.sendHttpPost(url,"@data.txt");
        System.out.println(result);
    }

    @Test
    public void testInsert2(){
        String url="http://192.168.0.129:8086/write?db=study";
        String result= HttpUtils.sendHttpPost(url,"people,id=3,name=wang age=18 1436005562000000000");
        System.out.println(result);
    }

    @Test
    public void testSelect(){
        String url="field/query?pretty=true&db=fsk&q=SELECT+*+FROM+fsk";
        String result=HttpUtils.sendHttpGet(url);
        System.out.println(result);
    }

    @Test
    public void TestParams(){
        String username = ReadUtil.get("username");
        String password = ReadUtil.get("password");
        String url = ReadUtil.get("url");
        String database = ReadUtil.get("database");
        String retentionPolicy = ReadUtil.get("retentionPolicy");
        System.out.println(username);
        System.out.println(retentionPolicy);
        System.out.println(System.currentTimeMillis());
    }
}
