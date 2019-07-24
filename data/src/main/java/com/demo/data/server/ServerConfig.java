package com.demo.data.server;

import com.demo.core.base.SlaveType;
import com.demo.core.untils.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** @Auther: zhouwenbin @Date: 2019/7/24 16:41 */
public class ServerConfig {
    private static final String SERVER_CONFIG_FILE = "server-config.properties";
    private String version;
    private int macID; // 物理机ID
    private int leaderPort; // 服务器管理器端口
    private String leaderHost; // 服务器管理器地址
    private String gameIP; // 游戏服IP
    private int gamePort; // 游戏端口
    private int backendPort; // 后台端口
    private String resUrl; // 资源文件路径
    private String logicUrl; // logic路径
    private String gameDBUrl;
    private String dbUsr;
    private String dbPwd;
    private boolean allGm;
    private SlaveType slaveType;
    private boolean dbSave;
    private boolean debug; // 调试模式
    private boolean logChat; // 是否记录聊天, 用于后台测试, 这个优先于机器人模式

    public void init() throws IOException {
        loadVersion();
        Properties properties = new Properties();
        InputStream stream;
        File file = new File(SERVER_CONFIG_FILE);
        if (file.exists()) {
            stream = new FileInputStream(file);
        } else {
            stream =
                    new FileInputStream(
                            this.getClass()
                                    .getResource(StringUtils.XIEGANG + SERVER_CONFIG_FILE)
                                    .getFile());
        }
        properties.load(stream);
        stream.close();
        this.macID = Integer.parseInt(properties.getProperty("id"));
        this.slaveType = Validate.notNull(SlaveType.valueOf(properties.getProperty("type")));
        this.leaderPort = Integer.parseInt(properties.getProperty("leader_port"));
        this.leaderHost = properties.getProperty("leader_host");
        this.gameIP = properties.getProperty("game_ip");
        this.gamePort = Integer.parseInt(properties.getProperty("game_port"));
        this.backendPort = Integer.parseInt(properties.getProperty("backend_port"));
        this.resUrl = properties.getProperty("res_url");
        this.gameDBUrl = properties.getProperty("db_game_url");
        this.dbUsr = properties.getProperty("db_usr");
        this.dbPwd = properties.getProperty("db_pwd");
        this.logChat = Integer.parseInt(properties.getProperty("chat_monitor")) == 1;
    }

    private void loadVersion() throws IOException {
        Properties properties = new Properties();
        InputStream stream = this.getClass().getResourceAsStream("/version");
        if (stream != null) {
            stream = new FileInputStream("data/src/main/resources/version");
        }
        properties.load(stream);
        stream.close();
        this.version = properties.getProperty("version");
    }
}
