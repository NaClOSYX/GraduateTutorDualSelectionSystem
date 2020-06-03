package com.naclo.dao.impl;


import com.naclo.dao.IdeaViewDao;
import com.naclo.pojo.IdeaView;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class IdeaViewDaoImpl implements IdeaViewDao {
    @Override
    public List<IdeaView> queryIdeas(Connection connection, String studentId, String teacherId, String major, int state) {
        List<IdeaView> ideaViewList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from ideaview";
            Object[] params = null;
            ArrayList<Object> paramsList = new ArrayList<>();
            Map<String, Object> paramsMap = new HashMap<>();
            if (null != studentId) {
                paramsMap.put("studentId", studentId);
            }
            if (null != teacherId) {
                paramsMap.put("teacherId", teacherId);
            }
            if (null != major) {
                paramsMap.put("majorName", major);
            }
            if (-1 != state) {
                paramsMap.put("state", state);
            }
            if (!paramsMap.isEmpty()) {
                sql += " where ";
                Iterator<Map.Entry<String, Object>> iterator = paramsMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> next = iterator.next();
                    String key = next.getKey();
                    Object value = next.getValue();
                    sql += " " + key + "=? ";
                    paramsList.add(value);
                    if (iterator.hasNext()) {
                        sql += " and ";
                    }
                }
            }
            System.out.println("sql = " + sql);
            params = paramsList.toArray();
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    IdeaView ideaView = new IdeaView();
                    ideaView.setIdeaId(rs.getInt(1));
                    ideaView.setMajorName(rs.getString(2));
                    ideaView.setStudentId(rs.getString(3));
                    ideaView.setStudentName(rs.getString(4));
                    ideaView.setTeacherId(rs.getString(5));
                    ideaView.setTeacherName(rs.getString(6));
                    ideaView.setState(rs.getInt(7));
                    ideaViewList.add(ideaView);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaViewList;
    }
}
