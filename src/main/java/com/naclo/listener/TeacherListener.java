package com.naclo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.naclo.pojo.Teacher;
import com.naclo.service.TeacherService;
import com.naclo.service.impl.TeacherServiceImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class TeacherListener extends AnalysisEventListener<Teacher> {
    Logger logger = Logger.getLogger(this.getClass());
    List<Teacher> teacherList = new ArrayList<Teacher>();

    //每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 100;
    private TeacherService teacherService;

    public TeacherListener() {
        teacherService = new TeacherServiceImpl();
    }

    @Override
    public void invoke(Teacher teacher, AnalysisContext context) {
        logger.info(JSON.toJSONString(teacher));
        teacherList.add(teacher);
        if (teacherList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            teacherList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        logger.info("所有数据解析完成！");
    }

    private void saveData() {
        logger.info(teacherList.size());
        for (Teacher teacher : teacherList) {
            teacherService.insertTeacher(teacher);
        }
        logger.info("存储数据库成功！");
    }
}
