package com.demo.basis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统工具
 * @Auther: zhouwenbin
 * @Date: 2019/8/16 21:53
 */
public final class SystemUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(System.class);

    public static final String FILE_ENCODING = getSystemProperty("file.encoding");
    public static final String JAVA_VERSION = getSystemProperty("java.version");

    private SystemUtils() {
    }

    private static String getSystemProperty(final String property) {
        return java.lang.System.getProperty(property);
    }
}
