package com.naclo;

import com.naclo.utils.MD5Utils;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;

/**
 * @Author NaClO
 * @create 2020/5/2 20:30
 */
public class MD5Test {

    @Test
    public void md5Test() throws Exception {
        String pwd = MD5Utils.stringToMD5("123456");
        System.out.println(123456);
    }
}
