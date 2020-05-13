package com.naclo;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * @Author NaClO
 * @create 2020/4/27 22:26
 */
public class loggerTest {
    private Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void loggerTest() {
        logger.info("info...");
        logger.debug("debug...");
        logger.error("error...");
        logger.warn("warn...");
    }
}
