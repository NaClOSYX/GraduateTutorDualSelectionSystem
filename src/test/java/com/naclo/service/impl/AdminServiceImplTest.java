package com.naclo.service.impl;


import com.naclo.pojo.Admin;
import com.naclo.service.AdminService;
import org.junit.jupiter.api.Test;

public class AdminServiceImplTest {
    AdminService adminService = new AdminServiceImpl();

    @Test
    void queryAllAdminsTest() {
        adminService.queryAllAdmins().forEach(System.out::println);
    }

    @Test
    void queryAllAdminsExceptAllTest() {
        adminService.queryAllAdminsExceptAll().forEach(System.out::println);
    }

    @Test
    void queryAdminsByAdminIdTest() {
        adminService.queryAdminsByAdminId("admin").forEach(System.out::println);
    }

    @Test
    void queryAdminsByMajorTest() {
        adminService.queryAdminsByMajor("软件工程").forEach(System.out::println);
    }

    @Test
    void insertAdminTest() {
        Admin admin = new Admin("sss", null, "软件工程");
        boolean flag = adminService.insertAdmin(admin);
        if (flag) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    void updateAdminPasswordByIdTest() {
        boolean flag = adminService.updateAdminPasswordById("sss", "123456");
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void updateAdminByIdTest() {
        Admin admin = new Admin("sss", null, "计算机科学与技术");
        boolean flag = adminService.updateAdminById(admin);
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void deleteAdminByIdTest() {
        boolean flag = adminService.deleteAdminById("sss");
        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
}