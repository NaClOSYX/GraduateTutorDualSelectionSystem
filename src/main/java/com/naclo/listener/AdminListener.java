package com.naclo.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.naclo.pojo.Admin;
import com.naclo.service.AdminService;
import com.naclo.service.impl.AdminServiceImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AdminListener extends AnalysisEventListener<Admin> {
    Logger logger = Logger.getLogger(this.getClass());
    List<Admin> adminList = new ArrayList<Admin>();

    //每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 5;
    private AdminService AdminService;

    public AdminListener() {
        AdminService = new AdminServiceImpl();
    }

    @Override
    public void invoke(Admin admin, AnalysisContext context) {
        logger.info(JSON.toJSONString(admin));
        adminList.add(admin);
        if (adminList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            adminList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        logger.info("所有数据解析完成！");
    }

    private void saveData() {
        logger.info(adminList.size());
        for (Admin admin : adminList) {
            AdminService.insertAdmin(admin);
        }
        logger.info("存储数据库成功！");
    }
}
