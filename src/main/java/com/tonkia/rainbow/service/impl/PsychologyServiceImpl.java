package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.PsychologyMapper;
import com.tonkia.rainbow.pojo.SayingInfo;
import com.tonkia.rainbow.service.PsychologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsychologyServiceImpl implements PsychologyService {
    @Autowired
    private PsychologyMapper psychologyMapper;

    @Override
    public List<SayingInfo> getAllSaying(Integer category) {
        return psychologyMapper.getAllSaying(category);
    }

    @Override
    public boolean insertSaying(SayingInfo sayingInfo) {
        return psychologyMapper.insertSaying(sayingInfo) > 0;
    }

}
