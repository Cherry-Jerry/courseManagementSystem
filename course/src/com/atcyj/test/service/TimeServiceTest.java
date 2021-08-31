package com.atcyj.test.service;

import com.atcyj.service.TimeService;
import com.atcyj.service.impl.TimeServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeServiceTest {
    TimeService timeService = new TimeServiceImpl();
    @Test
    public void isBeforeOpenTime() {
        System.out.println(timeService.isBeforeOpenTime());
    }
}