package com.naclo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author NaClO
 * @create 2020/4/27 22:13
 */
public class pTest {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/j2eeclass?serverTimezone=UTC&useSSL=true&useUnicode=true&characterEncoding=UTF-8";
    private static String username = "root";
    private static String password = "root";

    static {
        Properties properties = new Properties();
        //通过类加载器读取对应资源
        InputStream is = pTest.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    @Test
    public void pTest() {
        System.out.println(driver);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}
