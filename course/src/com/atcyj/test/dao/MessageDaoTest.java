package com.atcyj.test.dao;

import com.atcyj.dao.MessageDao;
import com.atcyj.dao.impl.MessageDaoImpl;
import com.atcyj.pojo.Message;
import org.junit.Test;

public class MessageDaoTest {
    MessageDao messageDao = new MessageDaoImpl();
    @Test
    public void queryMessageBySenderId() {
        System.out.println(messageDao.queryMessageBySenderId("7"));
    }

    @Test
    public void queryMessageByReceiverId() {
        System.out.println(messageDao.queryMessageByReceiverId("1"));
    }

    @Test
    public void addMessage() {
        System.out.println(messageDao.addMessage(new Message(7,1,"你好zhangsan，我是管理员")));
    }
}