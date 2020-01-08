package com.repay.app.service.impl;

import com.repay.app.dao.TestDao;
import com.repay.app.dao.entity.Test;
import com.repay.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public Test queryTest(Long id) {
        return testDao.getTest(id);
    }
}
