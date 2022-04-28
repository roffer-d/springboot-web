package com.roffer.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Roffer
 * @description 树形数据工具类
 * @date 2022/4/25 15:32
 */
public class TreeUtils {
    /**
     * @description 树形数据封装
     * @params: list(List): 要转化的数据集合
     * @params: idKey(String): id字段key
     * @params: rootPid(String): 根节点key
     * @params: pidKey(String): 父级节点key
     * @params: childrenKey(String): 要生成的子集key
     * @author Roffer
     * @date 2022/4/25 15:33
     */
    public static <T> List<Map<String,Object>> buildTree(List<T> list, String idKey, String rootPid, String pidKey, String childrenKey) {
        List<Map<String,Object>> rootList = new ArrayList<>();
        if(list.isEmpty()){
            return rootList;
        }

        list.forEach(item->{
            JSONObject obj = JsonUtils.object2JsonObject(item);
            if(obj.getString(pidKey).equals(rootPid)){
                rootList.add(JsonUtils.jsonObject2Map(obj));
            }
        });

        rootList.forEach(item->{
            String id = String.valueOf(item.get(idKey));
            String pid = String.valueOf(item.get(pidKey));
            List<Map<String,Object>> childList = getChildren(id,idKey,pidKey,childrenKey,list);
            item.put(childrenKey,childList);
        });

        return rootList;
    }

    private static List<Map<String,Object>> getChildren(
            String id,String idKey,String pidKey,String childrenKey,List all) {

        List<Map<String,Object>> childList = new ArrayList<>();

        for (Object item : all) {
            JSONObject obj = JsonUtils.object2JsonObject(item);
            if(obj.getString(pidKey).equals(id)){
                childList.add(JsonUtils.jsonObject2Map(obj));
            }
        }

        for (Map m : childList) {
            String cId = String.valueOf(m.get(idKey));
            m.put(childrenKey,getChildren(cId,idKey,pidKey,childrenKey,all));
        }

        if(childList.size()==0){
            return new ArrayList<>();
        }

        return childList;
    }
}
