package com.magnus.managee.main.business.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.magnus.managee.main.business.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JacksonMapperController {

    @RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() throws JsonProcessingException {

        Map map = new HashMap();
        map.put("name", "magnus");
        map.put("age", 24);
        map.put("date", new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        objectMapper.setDateFormat(yyyyMMdd);
        String s = objectMapper.writeValueAsString(map);
        return s;
    }

    @RequestMapping("/json2")
    public Map map() {
        Map map = new HashMap();
        map.put("name", "magnus");
        map.put("age", 24);
        return map;
    }

    @RequestMapping("/exception")
    public void shit() throws Exception {
        throw new Exception();
    }

    @RequestMapping("/fastjson")
    public Object shit(User user) {
        Map map = new HashMap();
        map.put("name", "magnus");
        map.put("age", 24);
        map.put("sex", "male");

        System.out.println(user);
        Object o = JSON.toJSON(user);
        System.out.println(o);
        String s = JSON.toJSONString(user);
        System.out.println(s);
        return o;
    }
}
