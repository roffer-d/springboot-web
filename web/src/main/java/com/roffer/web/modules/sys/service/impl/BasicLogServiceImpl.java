package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicLog;
import com.roffer.web.modules.sys.mapper.BasicLogMapper;
import com.roffer.web.modules.sys.service.BasicLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicLogServiceImpl extends ServiceImpl<BasicLogMapper,BasicLog> implements BasicLogService {
    @Resource
    private BasicLogMapper basicLogMapper;
}
