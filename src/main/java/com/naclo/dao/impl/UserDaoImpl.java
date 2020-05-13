package com.naclo.dao.impl;

import com.naclo.dao.UserDao;
import com.naclo.pojo.User;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImpl implements UserDao {

    @Override
    public User queryUserById(Connection connection, String id) {
        User user = new User();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from user where id = ?";
            Object[] paramas = {id};
            try {
                rs = DBUtil.query(connection, sql, pstm, paramas, rs);
                while (rs.next()) {
                    user.setId(rs.getString(1));
                    user.setPassword(rs.getString(2));
                    user.setMajor(rs.getString(3));
                    user.setRole(rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return user;
    }
}
