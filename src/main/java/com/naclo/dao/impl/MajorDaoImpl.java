package com.naclo.dao.impl;

import com.naclo.dao.MajorDao;
import com.naclo.pojo.Major;
import com.naclo.pojo.Student;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MajorDaoImpl implements MajorDao {
    @Override
    public List<Major> queryAllMajors(Connection connection) {
        List<Major> majorList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from major";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Major major = new Major();
                    major.setMajorId(rs.getInt(1));
                    major.setMajorName(rs.getString(2));
                    major.setStudentMax(rs.getInt(3));
                    majorList.add(major);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return majorList;
    }
}
