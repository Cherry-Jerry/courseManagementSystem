package com.atcyj.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;


public class WebUtils {

    private WebUtils() {}

    public static <T> T copyParamToBean(Map<String,String[]> map, T bean){

        Set<Map.Entry<String,String[]>> entrySet = map.entrySet();
        for(Map.Entry<String,String[]> entry : entrySet){
            String methodName = "set"+entry.getKey().substring(0,1).toUpperCase()+entry.getKey().substring(1);
            if ("setAction".equals(methodName)) {
                continue;
            }
            try {
                Method method = bean.getClass().getMethod(methodName,String.class);
                method.invoke(bean,entry.getValue()[0]);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
