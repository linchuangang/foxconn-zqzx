package com.inrevo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inrevo.util.InfluxDBUtil;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.junit.Test;

import java.util.*;

public class TestInsert {
    public static void main(String[] args) {
        InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
        Map<String, String> tags1 = new HashMap<String, String>();
        tags1.put("name", "lin");
        Map<String, String> tags2 = new HashMap<String, String>();
        tags2.put("name", "liu");
        Map<String, Object> fields1 = new HashMap<String, Object>();
        fields1.put("age", "11");
        // 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
        fields1.put("id", 123456);
        Map<String, Object> fields2 = new HashMap<String, Object>();
        fields2.put("age", "22");
        fields2.put("id", 7);
        // 一条记录值
        Point point1 = influxDBConnection.pointBuilder("pp", System.currentTimeMillis(), tags1, fields1);
        Point point2 = influxDBConnection.pointBuilder("pp", System.currentTimeMillis(), tags2, fields2);
        // 将两条记录添加到batchPoints中
        BatchPoints batchPoints1 = BatchPoints.database("study").build();
        batchPoints1.point(point1);
        BatchPoints batchPoints2 = BatchPoints.database("study").build();
        batchPoints2.point(point2);
        // 将不同的batchPoints序列化后，一次性写入数据库，提高写入速度
        List<String> records = new ArrayList<String>();
        records.add(batchPoints1.lineProtocol());
        records.add(batchPoints2.lineProtocol());
        // 将两条数据批量插入到数据库中
        influxDBConnection.batchInsert("study", "", InfluxDB.ConsistencyLevel.ALL, records);
    }


    @Test
    public void parse() {
        String s = "[{\"area\":1,\"fileds\":{\"field1\":1,\"field2\":2},\"model\":\"Shipment\",\"time\":1545992774843,\"tags\":{\"tag1\":\"parts1\"}},{\"area\":1,\"fileds\":{\"field1\":3,\"field2\":4},\"model\":\"Shipment\",\"time\":1545992774843,\"tags\":{\"tag1\":\"parts2\"}}]\n";
        JSONArray jsonArray = JSONArray.parseArray(s);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.getJSONObject(i);
            String model = jsonObject.getString("model");
            System.out.println("-" + model);
            String time = jsonObject.getString("time");
            System.out.println("--" + time);
            String area = jsonObject.getString("area");
            System.out.println("---" + area);
            Map<String, Object> fileds = (Map<String, Object>) JSONObject.parseObject(jsonObject.getString("fileds"));
            for (String filed : fileds.keySet()) {
                System.out.println("----" + fileds.get(filed));
                System.out.println("-----" + filed.substring(5));
            }
            Map<String, Object> tags = (Map<String, Object>) JSONObject.parseObject(jsonObject.getString("tags"));
            for (String tag : tags.keySet()) {
                System.out.println("------" + tags.get(tag));
                System.out.println("-------" + tag.substring(3));
            }
        }

    }

    //model time locale attribute value
    @Test
    public void create() {
        InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
        List<String> records = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -i);
            Map<String, String> tags1 = new HashMap<String, String>();
            tags1.put("model", "shipment");
            tags1.put("attribute", "planvalue");
            tags1.put("attribute", "actualValue");
            tags1.put("locale", "1");
            Map<String, Object> fields1 = new HashMap<String, Object>();
            fields1.put("value", (int) (1 + Math.random() * (10 - 1 + 1)));
            // 一条记录值
            Point point = influxDBConnection.pointBuilder("fsk", calendar.getTimeInMillis(), tags1, fields1);
            // 将两条记录添加到batchPoints中
            BatchPoints batchPoints = BatchPoints.database("test").build();
            batchPoints.point(point);
            // 将不同的batchPoints序列化后，一次性写入数据库，提高写入速度
            records.add(batchPoints.lineProtocol());
        }
        // 将数据批量插入到数据库中
        influxDBConnection.batchInsert("test", "", InfluxDB.ConsistencyLevel.ALL, records);
    }

    @Test
    public void sout() {
        System.out.println((int)(1 + Math.random() * (10)));
    }
}
