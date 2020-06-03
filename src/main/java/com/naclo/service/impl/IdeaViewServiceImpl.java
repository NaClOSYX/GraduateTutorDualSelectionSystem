package com.naclo.service.impl;

import com.naclo.dao.IdeaViewDao;
import com.naclo.dao.impl.IdeaViewDaoImpl;
import com.naclo.pojo.IdeaView;
import com.naclo.service.IdeaViewService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Author NaClO
 * @create 2020/6/3 11:45
 */
public class IdeaViewServiceImpl implements IdeaViewService {
    IdeaViewDao ideaViewDao = new IdeaViewDaoImpl();

    @Override
    public List<IdeaView> queryIdeas(String studentId, String teacherId, String major, int state) {
        Connection connection = DBUtil.getConnection();
        List<IdeaView> ideaViewList = ideaViewDao.queryIdeas(connection, studentId, teacherId, major, state);
        DBUtil.closeResource(connection, null, null);
        return ideaViewList;
    }
}
