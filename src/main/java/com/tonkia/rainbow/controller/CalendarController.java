package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.CalendarInfo;
import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.CalendarService;
import com.tonkia.rainbow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;
    @Autowired
    UserService userService;

    @RequestMapping("addItem")
    @ResponseBody
    public ResponseMessage addItem(CalendarInfo calendarInfo, String phoneNumber, String token) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo != null) {
            calendarInfo.setUid(userInfo.getUid());
            if (calendarService.insertItem(calendarInfo)) {
                List<CalendarInfo> list = calendarService.getItem(userInfo.getUid());
                return new ResponseMessage("日历记录成功！", list, ResponseMessage.SUCCESS);
            } else {
                return new ResponseMessage("日历记录失败！", null, ResponseMessage.FAILURE);
            }
        } else {
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
        }
    }

    @RequestMapping("getItem")
    @ResponseBody
    public ResponseMessage getItem(String phoneNumber, String token) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo != null) {
            List<CalendarInfo> list = calendarService.getItem(userInfo.getUid());
            if (list == null || list.size() == 0)
                return new ResponseMessage("数据为空", list, ResponseMessage.FAILURE);
            return new ResponseMessage("返回成功", list, ResponseMessage.SUCCESS);
        } else {
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
        }
    }
}
