package com.atcyj.test.utils;

import com.atcyj.pojo.User;
import com.atcyj.utils.WebUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebUtilsTest {

    @Test
    public void copyParamToBean() {
        Map map = new HashMap<String,String[]>();
        String[] s1 = new String[2];
        String[] s2 = new String[2];
        s1[0]="zhangsan";
        s2[0]="123456";
        map.put("username",s1);
        map.put("password",s2);

        User user = WebUtils.copyParamToBean(map,new User());
        System.out.println(user);
    }
}