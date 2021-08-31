package com.atcyj.service;

/**
 * @author chenyujie
 */
public interface TimeService {

    /**
     * 判断当前时间是否在 course/web/timeConfig.txt中的openTime 之前
     * @return 是返回true，否则返回false
     */
    boolean isBeforeOpenTime();
}
