package com.roffer.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roffer
 * @description JSON转化类
 * @date 2022/4/28 16:10
 */
public class JsonUtils {
    /**
      * @description Object转JSONObject
      * @params:
      *   data(Object): 要转化的数据
      * @author Roffer
      * @date 2022/4/28 16:11
      */
    public static JSONObject object2JsonObject(Object data){
        return JSONObject.parseObject(JSON.toJSONStringWithDateFormat(data,"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat));
    }

    /**
      * @description JSONObject转Map
      * @params:
      *   jsonObject2Map(JSONObject): 描述
      * @author Roffer
      * @date 2022/4/28 16:11
      */
    public static Map<String,Object> jsonObject2Map(JSONObject data){
        Map<String,Object> map = new HashMap();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
