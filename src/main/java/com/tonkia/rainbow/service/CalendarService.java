package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.CalendarInfo;

import java.util.List;

public interface CalendarService {
    boolean insertItem(CalendarInfo calendarInfo);

    List<CalendarInfo> getItem(Integer uid);
}
