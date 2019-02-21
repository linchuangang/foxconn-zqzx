package com.inrevo;


import com.alibaba.fastjson.JSON;
import com.inrevo.util.HttpUtils;
import com.inrevo.util.InfluxDBUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestInsertSingle {
    //model time area tag1 tag2 tag3 tag4 filed1 filed2 filed3 filed4
    public static void main(String[] args) {
        Map<String, String> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("tag1", "parts1");
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("field1", 1);
        fields.put("field2", 2);
        map.put("tags", tags);
        map.put("fileds", fields);
        map.put("model", "Shipment");
        map.put("area", 1);
        map.put("time", System.currentTimeMillis());
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        Map<String, String> tags1 = new HashMap<String, String>();
        tags1.put("tag1", "parts2");
        Map<String, Object> fields1 = new HashMap<String, Object>();
        fields1.put("field1", 3);
        fields1.put("field2", 4);
        map1.put("tags", tags1);
        map1.put("fileds", fields1);
        map1.put("time", System.currentTimeMillis());
        map1.put("model", "Shipment");
        map1.put("area", 1);
        list.add(map1);
        result.put("result",JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(list));
        System.out.println(result);
//        String over = HttpUtils.sendHttpPost("http://localhost:8080/fsk/add",result);
//        System.out.println(over);
    }

    @Test
    public void testTime() {
        for(int i=0;i<1;i++){
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -(int) (1 + Math.random() * (30)));
            InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
            Map<String, String> tags = new HashMap<String, String>();
            tags.put("model", "shipment");
            tags.put("area", "1");
            tags.put("tag1", "parts"+(int) (1 + Math.random() * (100)));
            Map<String, Object> fields = new HashMap<String, Object>();
            fields.put("field1", (int) (1 + Math.random() * (1000)));
            fields.put("field2",  (int) (1 + Math.random() * (1000)));
            // 时间使用毫秒为单位
            influxDBConnection.insert("fsk", tags, fields, calendar.getTimeInMillis(), TimeUnit.MILLISECONDS);

        }


    }
}
