package com.naclo.service.impl;

import com.naclo.pojo.User;
import com.naclo.service.UserService;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    void queryUserByIdTest() {
        User user = userService.queryUserById("20171422");
        System.out.println(user);
    }
}