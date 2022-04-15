package com.zpy.bilibili.api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zpy-bilibili
 * @description: RESTfullApi
 * @author: 张鹏宇
 * @create: 2022-04-16 01:16
 **/
@RestController
public class RESTfullApi {

    private final Map<Integer, Map<String, Object>> dataMap;

    public RESTfullApi() {
        dataMap = new HashMap();
        for (int i = 1; i < 3; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "name" + i);
            dataMap.put(i, data);
        }
    }

    @GetMapping("/objects/{id}")
    public Map<String, Object> getData(@PathVariable Integer id) {
        return dataMap.get(id);
    }

    @DeleteMapping("/objects/{id}")
    public String deleteData(
            @PathVariable Integer id) {
        dataMap.remove(id);
        return "delete success";
    }

    @PostMapping("/objects")
    public String postData(@RequestBody Map<String, Object> data){
        Integer[] idArray = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(idArray);
        int nextId = idArray[idArray.length-1]+1;
        dataMap.put(nextId,data);
        return "post success";

    }
    @PutMapping("/objects")
    public String putData(@RequestBody Map<String, Object> data){
        Integer id = Integer.valueOf(String.valueOf(data.get("id")));
        Map<String, Object> content = dataMap.get(id);
        if(content == null){
            Integer[] idArray = dataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(idArray);
            int nextId = idArray[idArray.length-1]+1;
            dataMap.put(nextId,data);
        }else{
            dataMap.put(id,data);
        }
        return "put success";
    }
}
