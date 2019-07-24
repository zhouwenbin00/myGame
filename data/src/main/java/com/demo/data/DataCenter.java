package com.demo.data;

import com.demo.data.server.ServerConfig;

/** @Auther: zhouwenbin @Date: 2019/7/24 16:38 */
public class DataCenter {
    public ServerConfig serverConfig = new ServerConfig();


    private static DataCenter dataCenter = new DataCenter();

    public static DataCenter getInstance() {
        return dataCenter;
    }
}
