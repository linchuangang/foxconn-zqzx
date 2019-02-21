package com.inrevo.controller;

import com.alibaba.fastjson.JSON;
import com.inrevo.util.*;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fsk")
public class FskController {

    private static final Logger logger = LoggerFactory.getLogger(FskController.class);

    String database = ReadUtil.get("database");
    String measurement = ReadUtil.get("measurement");
    String ecode = ReadUtil.get("ecode");

    // model time area tag1 tag2 tag3 tag4 field1 field2 field3 field4 field5(double) field6(double)
    //http://localhost:8080/fsk/add?model=Department&area=1&type=2&time=2018-12-28&name=%E6%9D%90%E6%96%99%E9%83%A8&productionValue=10&upph=2&actualValue=1&depreciationCost=8
    //http://localhost:8080/fsk/add?model=Yield&area=1&type=2&time=2018-12-29&partName=part1&fixture=line1&planValue=2&actualValue=1
    //http://localhost:8080/fsk/add?model=Shipment&area=1&type=2&time=2018-12-29&partName=part2&planValue=10&actualValue=2
    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String add(HttpServletRequest request) {
        InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
        Map<String, Map<String, String>> mappingMap = ModelMappingUtil.getMapping();
        //存储tags
        Map<String, String> tags = new HashMap<String, String>();
        //存储fields
        Map<String, Object> fields = new HashMap<String, Object>();
        String model = request.getParameter("model");
        logger.info("===model===" + model);
        Map<String, String> modelMap = mappingMap.get(model);
        if (modelMap == null) {
            return JsonMessageUtils.getErrorJson("model不匹配");
        }
        tags.put("model", model);
        String area = request.getParameter("area");
        tags.put("area", area);
        String type = request.getParameter("type");
        String time = request.getParameter("time");
        long timeMillis = 0L;
        try {
            if (type == null || type.isEmpty() || type.equals("1")) {//传入时间到毫秒
                timeMillis = Long.parseLong(time);
            } else if (type.equals("2")) {//传入时间到日 yyyy-MM-dd
                Date date = DateUtils.parseByDate(time);
                timeMillis = date.getTime();
            } else if (type.equals("3")) {//穿入时间到月yyyy-MM
                Date date = DateUtils.parseByMonth(time);
                timeMillis = date.getTime();
            } else {
                return JsonMessageUtils.getErrorJson("type 不匹配");
            }
        } catch (NumberFormatException e) {
            return JsonMessageUtils.getErrorJson("时间格式不匹配");
        }
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paramName = (String) enu.nextElement();
            String key = modelMap.get(paramName.trim());
            String value = request.getParameter(paramName);
            logger.info("key====" + key + "---value===" + value);
            try {
                value = new String(value.getBytes(ecode), "UTF-8");
                logger.info("转码后：key====" + key + "---value===" + value);
            } catch (UnsupportedEncodingException e) {
                return JsonMessageUtils.getErrorJson("编码装换异常");
            }
            if (key != null && !key.isEmpty()) {
                if (key.contains("tag")) {
                    tags.put(key, value);
                } else if (key.contains("field")) {
                    if (value.contains(".")) {
                        fields.put(key, Double.parseDouble(value));
                    } else {
                        fields.put(key, Integer.parseInt(value));
                    }
                }
            }
        }
        if (fields.size() == 0) {
            return JsonMessageUtils.getErrorJson("field参数不足");
        }
        logger.info("tags==" + JSON.toJSONString(tags) + "-----fields==" + JSON.toJSONString(fields));
        // 时间使用毫秒为单位
        influxDBConnection.insert(measurement, tags, fields, timeMillis, TimeUnit.MILLISECONDS);
        return JsonMessageUtils.getSuccessJson("添加成功");
    }

    @RequestMapping(value = "/select", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String select(String sql) {
//        QueryResult.Result oneResult = null;
        QueryResult results = null;
        try {
            if (sql == null || sql.isEmpty()) {
                return JsonMessageUtils.getErrorJson("sql不能为空");
            }
            logger.info("sql===" + sql);
            sql = new String(sql.getBytes(ecode), "UTF-8");
            logger.info("转码后：sql===" + sql);
            InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
            results = influxDBConnection.query(sql);
//            System.out.println(JSON.toJSONString(results));
            //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
//            oneResult = results.getResults().get(0);
//            System.out.println(JSON.toJSONString(oneResult));
//            if (oneResult.getSeries() != null) {
//                List<List<List<Object>>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues).collect(Collectors.toList());
//                System.out.println(JSON.toJSONString(valueList));
//                if (valueList != null && valueList.size() > 0) {
//                    for (List<List<Object>> values : valueList) {
//                        System.out.println(JSON.toJSONString(values));
//                    }
//                }
//            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonMessageUtils.getErrorJson("error:" + e.getMessage());
        }
        return JsonMessageUtils.getObjectJson(results);
    }
}
