package com.demo.basis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @Auther: zhouwenbin
 * @Date: 2019/8/16 22:47
 */
public class TimeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);

    /**
     * 格式YYYY-MM-DD HH:mm:SS
     *
     * @param str
     * @return
     */
    public static LocalDateTime parse(String str) {
        return LocalDateTime.parse(str.replaceAll(" ", "T"));
    }
}
