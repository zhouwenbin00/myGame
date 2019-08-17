package com.demo.basis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:46
 */
public final class StringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
    private static final String placeholder = "{}";

    private StringUtils() {
    }

    public static String format(String s, Object... params) {
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            int index = s.indexOf(placeholder);
            if (index != -1) {
                String substring = s.substring(0, index);
                sb.append(substring).append(param.toString());
                s = s.substring(index + placeholder.length());
            }
        }
        return sb.toString();
    }

}
