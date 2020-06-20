package com.naclo;

import com.naclo.utils.MD5Util;
import org.junit.jupiter.api.Test;

/**
 * @Author NaClO
 * @create 2020/5/2 20:30
 */
public class MD5Test {

    @Test
    public void md5Test() throws Exception {
        String pwd = MD5Util.stringToMD5("123456");
        System.out.println(123456);
    }
}
