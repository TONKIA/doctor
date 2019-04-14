package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.CalendarMapper;
import com.tonkia.rainbow.pojo.CalendarInfo;
import com.tonkia.rainbow.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    CalendarMapper calendarMapper;

    @Override
    public boolean insertItem(CalendarInfo calendarInfo) {
        calendarInfo.setTime(new Date());
        return calendarMapper.insertItem(calendarInfo);
    }

    @Override
    public List<CalendarInfo> getItem(Integer uid) {
        return calendarMapper.getItem(uid);
    }
}
