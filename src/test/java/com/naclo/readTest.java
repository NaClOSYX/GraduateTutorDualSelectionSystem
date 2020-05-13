package com.naclo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.naclo.pojo.DemoData;
import com.naclo.pojo.DemoDataListener;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @Author NaClO
 * @create 2020/5/2 21:26
 */
public class readTest {
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "D:\\MyPrograms\\Java\\IdeaProjects\\GraduateTutorDualSelectionSystem\\write.xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
