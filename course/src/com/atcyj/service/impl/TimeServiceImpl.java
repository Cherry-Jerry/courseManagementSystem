package com.atcyj.service.impl;

import com.atcyj.service.TimeService;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author chenyujie
 */
public class TimeServiceImpl implements TimeService {
    @Override
    public boolean isBeforeOpenTime() {
        Properties properties = new Properties();
        FileReader fr;
        try {
            fr = new FileReader("D:\\IdeaProjects\\StudentManagementSystem\\course\\src\\com\\atcyj\\web\\timeConfig.txt");
            properties.load(fr);
            String stringOpenTime = properties.getProperty("openTime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date dateOpenTime = sdf.parse(stringOpenTime);
            Date nowDate = new Date();
            fr.close();
            return nowDate.before(dateOpenTime);
        } catch (IOException |ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
