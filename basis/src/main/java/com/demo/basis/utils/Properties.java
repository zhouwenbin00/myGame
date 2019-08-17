package com.demo.basis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 配置文件
 * @Auther: zhouwenbin
 * @Date: 2019/8/16 22:04
 */
public class Properties extends java.util.Properties {
    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);
    private final java.util.Properties properties;

    public Properties(java.util.Properties properties) {
        this.properties = properties;
    }

    /**
     * 获取一个配置文件
     *
     * @param classLoader
     * @param filePath
     * @return
     */
    public static Properties create(ClassLoader classLoader, String filePath) {
        java.util.Properties properties = new java.util.Properties();
        InputStream in = null;
        try {
            // 直接找文件
            File file = new File(filePath);
            if (file.canRead()) {
                in = new BufferedInputStream(new FileInputStream(file));
            } else {
                // 找不到再从路径中获取文件流
                in = classLoader.getResourceAsStream(filePath);
            }
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return new Properties(properties);
    }

    /**
     * 获取一个int值
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return Integer.parseInt(this.properties.getProperty(key));
    }

    /**
     * 获取一个lang值
     *
     * @param key
     * @return
     */
    public long getLong(String key) {
        return Long.parseLong(this.properties.getProperty(key));
    }

    /**
     * 获取一个字符串
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return this.properties.getProperty(key);
    }

}
