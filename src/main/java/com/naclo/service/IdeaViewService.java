package com.naclo.service;


import com.naclo.pojo.IdeaView;

import java.util.List;

public interface IdeaViewService {
    List<IdeaView> queryIdeas(String studentId, String teacherId, String major, int state);

}
