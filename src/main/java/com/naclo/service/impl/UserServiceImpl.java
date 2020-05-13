package com.naclo.service.impl;

import com.naclo.dao.UserDao;
import com.naclo.dao.impl.UserDaoImpl;
import com.naclo.pojo.User;
import com.naclo.service.UserService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author NaClO
 * @create 2020/5/7 13:51
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User queryUserById(String id) {
        Connection connection = connection = DBUtil.getConnection();
        User user = userDao.queryUserById(connection, id);
        DBUtil.closeResource(connection, null, null);
        return user;
    }
}
