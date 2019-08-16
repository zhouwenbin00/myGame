package com.demo.data;

import com.demo.basis.utils.Charset;
import com.demo.basis.utils.SystemUtils;
import com.demo.data.server.ServerConfig;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * data层入口
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/16 21:34
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        ServerConfig.DATA_BOOT = true;
        Preconditions.checkArgument(
                SystemUtils.FILE_ENCODING.equalsIgnoreCase(Charset.utf_8),
                "字符集必须为utf-8");
        //加载配置文件
        DataCenter.getInstance().serverConfig.init();
    }
}
