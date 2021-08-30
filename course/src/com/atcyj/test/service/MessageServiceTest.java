package com.atcyj.test.service;

import com.atcyj.pojo.User;
import com.atcyj.service.MessageService;
import com.atcyj.service.impl.MessageServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageServiceTest {
    MessageService messageService = new MessageServiceImpl();
    User sender = new User(1,"zhangsan","123456");
    User receiver = new User(7,"admin","admin");
    @Test
    public void sendMessage() {
        messageService.sendMessage(sender,receiver,"zhangsan收到了管理员的信息");
    }

    @Test
    public void showMessageTo() {
        System.out.println(messageService.showMessageTo(receiver));
    }

    @Test
    public void showMessageFrom() {
        System.out.println(messageService.showMessageFrom(sender));
    }
}