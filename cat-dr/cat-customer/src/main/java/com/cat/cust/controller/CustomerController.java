package com.cat.cust.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cat.cust.controller.from.RegisterCustomerForm;
import com.cat.cust.service.CustomerService;
import com.cat.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 乘客服务端
 */
@RestController
@RequestMapping("/customer")
@Tag(name = "CustomerController", description = "客户核心业务处理接口")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/register")
    @Operation(summary = "新用户注册接口")
    public R registerCustomer(@RequestBody @Valid RegisterCustomerForm form) {
        Map map = BeanUtil.beanToMap(form);
        String userId = customerService.registerCustomer(map);
        return R.ok().put("userId", userId);

    }

}

