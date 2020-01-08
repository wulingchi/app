package com.repay.app.controller;

import com.repay.app.controller.base.CommonApiResult;
import com.repay.app.dao.entity.Test;
import com.repay.app.service.TestService;
import com.repay.app.vo.TestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.BeanMapper;
import redis.clients.jedis.JedisPool;

@Api(value="swagger测试", description="TestController")
@RestController
@RequestMapping("/my")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private JedisPool jedisPool;

    @ApiOperation(value="获取测试vo",notes="获取测试vo描述信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "测试id", required = true, dataType = "Integer")
    })
    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public CommonApiResult<TestVo> test(Long id){
        Test test = testService.queryTest(id);
     /*   jedisPool.getResource().set("1","2");
        String a = jedisPool.getResource().get("1");
        System.out.println(a);*/
        return CommonApiResult.success(BeanMapper.map(test,TestVo.class));
    }


}
