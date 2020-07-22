/**
 * projectName: WeChatWorkApiTest
 * fileName: FakerUtils.java
 * packageName: com.wecht.utils
 * date: 2020-07-18 4:22 下午
 */
package com.wechat.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: FakerUtils
 * @packageName: com.wecht.utils
 * @description: faker伪造工具
 * @data: 2020-07-18 4:22 下午
 **/
public class FakerUtils {
    private static final Logger logger = LoggerFactory.getLogger(FakerUtils.class);

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    @Test
    void test() {
        logger.info(getTimeStamp());
    }
}