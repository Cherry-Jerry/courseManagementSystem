package com.atcyj.service.impl;

import com.atcyj.dao.MessageDao;
import com.atcyj.dao.impl.MessageDaoImpl;
import com.atcyj.pojo.Message;
import com.atcyj.pojo.User;
import com.atcyj.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    MessageDao messageDao = new MessageDaoImpl();

    @Override
    public int sendMessage(User sender, User receiver, String msg) {
        Message message = new Message(sender.getId(),receiver.getId(),msg);
        return messageDao.addMessage(message);
    }

    @Override
    public List<Message> showMessageTo(User receiver) {
        return messageDao.queryMessageByReceiverId(receiver.getId().toString());
    }

    @Override
    public List<Message> showMessageFrom(User sender) {
        return messageDao.queryMessageBySenderId(sender.getId().toString());
    }
}
