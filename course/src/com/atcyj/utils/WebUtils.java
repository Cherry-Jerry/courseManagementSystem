package com.atcyj.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author chenyujie
 */
public class WebUtils {

    private WebUtils() {}

    public static <T> T copyParamToBean(Map<String,String[]> map, T bean){

        Set<Map.Entry<String,String[]>> entrySet = map.entrySet();
        Method[] methods = bean.getClass().getMethods();

        for(Map.Entry<String,String[]> entry : entrySet){
            String methodName = "set"+entry.getKey().substring(0,1).toUpperCase()+entry.getKey().substring(1);
            if ("setAction".equals(methodName)) {
                continue;
            }

            try {
                // @todo getMethod 的参数不一定是string类型
                try {
                    // 试试String 的set方法
                    Method method = bean.getClass().getMethod(methodName,String.class);
                    method.invoke(bean,entry.getValue()[0]);
                } catch (NoSuchMethodException e) {
                    try {
                        // 试试Integer 的set方法
                        Method method = bean.getClass().getMethod(methodName,Integer.class);
                        method.invoke(bean,Integer.parseInt(entry.getValue()[0]));
                    } catch (NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
