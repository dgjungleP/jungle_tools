package com.jungle.tools.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonUtil {
    private JsonUtil() {
    }

    public static List<Operate> getJsonPath(String pathStr) {
        String[] operateList = pathStr.split("\\.");
        return Arrays.stream(operateList).map((data) -> {
            Operate operate = new Operate();
            Method method = null;
            if (data.startsWith("[")) {
                try {
                    method = JSONArray.class.getMethod("get", int.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                data = data.replaceAll("[\\[\\]]", "");
            } else {
                try {
                    method = JSONObject.class.getMethod("get", Object.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            operate.keyName = data;
            operate.method = method;
            return operate;
        }).collect(Collectors.toList());
    }

    public static class Operate {
        private Method method;
        private String keyName;
        private Object json;

        public Object operate(Object preObject) {
            Object invoke;
            try {
                Class<?> parameterType = method.getParameterTypes()[0];
                invoke = method.invoke(preObject, parameterType.getName().equals("int") ? Integer.parseInt(keyName) : keyName);
            } catch (IllegalAccessException | InvocationTargetException e) {
                return "You get a error json path";
            }
            return invoke;
        }
    }

    public static JSONObject copyJsonValue(JSONObject target, JSONObject source) {
        Map<String, Object> targetInnerMap = target.getInnerMap();
        Map<String, Object> sourceInnerMap = source.getInnerMap();
        for (String simpleKey : targetInnerMap.keySet()) {
            Object mergeResult;
            Object targetSource = targetInnerMap.get(simpleKey);
            Object sourceValue = sourceInnerMap.get(simpleKey);
            if (Objects.isNull(sourceValue)) {
                continue;
            }
            if (sourceValue instanceof JSONObject && targetSource instanceof JSONObject) {
                mergeResult = copyJsonValue((JSONObject) targetSource, (JSONObject) sourceValue);
            } else {
                mergeResult = sourceValue;
            }
            targetInnerMap.put(simpleKey, mergeResult);
        }
        return target;
    }

}
