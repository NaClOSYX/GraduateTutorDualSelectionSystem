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

    private static final int BATCH_COUNT = 100;
    private AdminService adminService;

    public AdminListener() {
        adminService = new AdminServiceImpl();
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
            adminService.insertAdmin(admin);
        }
        logger.info("存储数据库成功！");
    }
}
