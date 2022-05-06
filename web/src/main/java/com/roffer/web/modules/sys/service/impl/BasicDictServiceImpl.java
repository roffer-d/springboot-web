package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicDict;
import com.roffer.web.modules.sys.mapper.BasicDictMapper;
import com.roffer.web.modules.sys.service.BasicDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicDictServiceImpl extends ServiceImpl<BasicDictMapper,BasicDict> implements BasicDictService {
    @Resource
    private BasicDictMapper basicDictMapper;
}
