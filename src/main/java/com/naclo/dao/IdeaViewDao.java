package com.naclo.dao;


import com.naclo.pojo.IdeaView;

import java.sql.Connection;
import java.util.List;

public interface IdeaViewDao {
    List<IdeaView> queryIdeas(Connection connection, String studentId, String teacherId, String major, int state);

}
