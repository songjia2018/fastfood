package com.jonas.fastfood.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.model.user.User;
import com.jonas.fastfood.common.service.user.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FastfoodUserApplicationTests {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user = userService.login(user);
    }

}
