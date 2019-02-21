package com.inrevo.controller;

import com.alibaba.fastjson.JSON;
import com.inrevo.util.InfluxDBUtil;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String findShiftList(String name) {
        logger.info("----" + name);
        return "helloWord";
    }


    @RequestMapping("/createDatabase")
    @ResponseBody
    public void createDatabase(String tableName) {
        InfluxDBUtil influxDBUtil = InfluxDBUtil.getInstance();
        influxDBUtil.createDB(tableName);
    }

    @RequestMapping("/dropDatabase")
    @ResponseBody
    public void dropDatabase(String tableName) {
        InfluxDBUtil influxDBUtil = InfluxDBUtil.getInstance();
        influxDBUtil.deleteDB(tableName);

    }

    @RequestMapping("/add")
    @ResponseBody
    public void add() {
        InfluxDBUtil influxDBUtil = InfluxDBUtil.getInstance();
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("name", "lin");
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("age", "18");
        // 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
        fields.put("id", 66);
        // 时间使用毫秒为单位
        influxDBUtil.insert("fiweb", tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @RequestMapping("/find")
    @ResponseBody
    public void find() {
        InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
        QueryResult results = influxDBConnection.query("select sum(num) from student group by id");
        System.out.println(JSON.toJSONString(results));
        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
        QueryResult.Result oneResult = results.getResults().get(0);
        System.out.println(JSON.toJSONString(oneResult));
        if (oneResult.getSeries() != null) {
            List<List<List<Object>>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues).collect(Collectors.toList());
            System.out.println(JSON.toJSONString(valueList));
            if (valueList != null && valueList.size() > 0) {
                for (List<List<Object>> values : valueList) {
                    System.out.println(JSON.toJSONString(values));
                }
            }
        }
    }


    @RequestMapping("/addBatch")
    @ResponseBody
    public void addBatch() {
        InfluxDBUtil influxDBUtil = InfluxDBUtil.getInstance();
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
        Point point1 = influxDBUtil.pointBuilder("pp", System.currentTimeMillis(), tags1, fields1);
        Point point2 = influxDBUtil.pointBuilder("pp", System.currentTimeMillis(), tags2, fields2);
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
        influxDBUtil.batchInsert("study", "", InfluxDB.ConsistencyLevel.ALL, records);
    }

}
