package com.inrevo;


import com.alibaba.fastjson.JSON;
import com.inrevo.util.InfluxDBUtil;
import org.influxdb.dto.QueryResult;

import java.util.List;
import java.util.stream.Collectors;

public class TestSelect {
    public static void main(String[] args) {
        InfluxDBUtil influxDBConnection = InfluxDBUtil.getInstance();
        QueryResult results = influxDBConnection.query("select * from fsk");
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


}
