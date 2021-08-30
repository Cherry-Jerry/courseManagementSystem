package com.atcyj.utils;

import javax.servlet.http.Cookie;

public class CookiesUtils {

    public static Cookie findCookies(String name ,Cookie[] cookies){
        if(name == null || cookies == null || cookies.length == 0){
            return null;
        }
        for (Cookie cookie : cookies){
            if(name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
