package com.atcyj.test.dao;

import com.atcyj.dao.LearnDao;
import com.atcyj.dao.impl.LearnDaoImpl;
import org.junit.Test;

public class LearnDaoTest {

    LearnDao learnDao = new LearnDaoImpl();
    @Test
    public void add() {
        learnDao.add(1,7);
    }

    @Test
    public void delete() {
        learnDao.delete(1,7);
    }

    @Test
    public void existRecord() {
        System.out.println(learnDao.queryRecord(1,9));
    }

    @Test
    public void selectedCourseNumber() {
        System.out.println(learnDao.selectedCourseNumber(9));
    }

    @Test
    public void queryLearnByStudentId() {
        Integer studentId = 3;
        System.out.println(learnDao.queryLearnByStudentId(studentId));
    }
}