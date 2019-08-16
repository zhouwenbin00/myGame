package com.demo.data.server;

import com.demo.basis.utils.Properties;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Auther: zhouwenbin
 * @Date: 2019/8/16 21:42
 */
@Singleton
public class ServerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfig.class);
    public final String SERVER_CONFIG_FILE = "server-config.properties";
    private static final String SERVERS_INFO_FILE = "servers.info";
    public String version; // 游戏版本号
    public static boolean DATA_BOOT; // 是否从data启动
    public int game_port; // 游戏端口
    public int server_id; // 区服id
    public long open_time; // 开服时间
    public String game_ip; // 游戏ip
    public int macID; // 物理机ID

    public void init() throws IOException {
        // 加载版本
        loadVersion();

        Properties properties = Properties.create(
                this.getClass().getClassLoader(), SERVER_CONFIG_FILE);
        // 赋值
        this.game_port = properties.getInt("game_port");
        this.game_ip = properties.getString("game_ip");
        this.macID = properties.getInt("macID");
        this.server_id = properties.getInt("server_id");
        this.open_time = properties.getLong("open_time");
        //...

        // 设置系统变量
        System.setProperty("game.id", properties.getString("macID"));


    }

    private void loadVersion() {
        Properties properties = Properties.create(
                this.getClass().getClassLoader(), "version");
        this.version = properties.getString("version");

    }
}
