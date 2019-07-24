package com.demo.data.server;

import com.demo.core.base.SlaveType;
import com.demo.core.untils.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** @Auther: zhouwenbin @Date: 2019/7/24 16:41 */
public class ServerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfig.class);

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
    private int sessionOverTime = 600; // 链接过期时间(内网10分钟嘛，让你们尽情调试)

    private Map<Integer, SingleServer> servers;

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
                                    .getResource(StringUtils.XIEXIAN + SERVER_CONFIG_FILE)
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
        System.setProperty("game.id", properties.getProperty("id"));

        switch (properties.getProperty("mode")) {
            case "RELEASE":
                setAllGm(false);
                setDbSave(true);
                setDebug(false);
                setSessionOverTime(60);
                break;
            case "DEBUG":
                setAllGm(true);
                setDbSave(true);
                setDebug(true);
                break;
            case "MEMORY":
                setAllGm(true);
                setDbSave(false);
                setDebug(true);
                break;
        }
        if (getSlaveType() == SlaveType.SHARE) {
            setDbSave(false);
            servers = new HashMap<>();
            LOGGER.info("SHARE服务器启动！");
        } else {
            initServers();
            //      initPlatforms();
        }
    }

    private void initServers() {}

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

    // --------------------------------------------------------

    public int getSessionOverTime() {
        return sessionOverTime;
    }

    public void setSessionOverTime(int sessionOverTime) {
        this.sessionOverTime = sessionOverTime;
    }

    public static String getServerConfigFile() {
        return SERVER_CONFIG_FILE;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getMacID() {
        return macID;
    }

    public void setMacID(int macID) {
        this.macID = macID;
    }

    public int getLeaderPort() {
        return leaderPort;
    }

    public void setLeaderPort(int leaderPort) {
        this.leaderPort = leaderPort;
    }

    public String getLeaderHost() {
        return leaderHost;
    }

    public void setLeaderHost(String leaderHost) {
        this.leaderHost = leaderHost;
    }

    public String getGameIP() {
        return gameIP;
    }

    public void setGameIP(String gameIP) {
        this.gameIP = gameIP;
    }

    public int getGamePort() {
        return gamePort;
    }

    public void setGamePort(int gamePort) {
        this.gamePort = gamePort;
    }

    public int getBackendPort() {
        return backendPort;
    }

    public void setBackendPort(int backendPort) {
        this.backendPort = backendPort;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getLogicUrl() {
        return logicUrl;
    }

    public void setLogicUrl(String logicUrl) {
        this.logicUrl = logicUrl;
    }

    public String getGameDBUrl() {
        return gameDBUrl;
    }

    public void setGameDBUrl(String gameDBUrl) {
        this.gameDBUrl = gameDBUrl;
    }

    public String getDbUsr() {
        return dbUsr;
    }

    public void setDbUsr(String dbUsr) {
        this.dbUsr = dbUsr;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public boolean isAllGm() {
        return allGm;
    }

    public void setAllGm(boolean allGm) {
        this.allGm = allGm;
    }

    public SlaveType getSlaveType() {
        return slaveType;
    }

    public void setSlaveType(SlaveType slaveType) {
        this.slaveType = slaveType;
    }

    public boolean isDbSave() {
        return dbSave;
    }

    public void setDbSave(boolean dbSave) {
        this.dbSave = dbSave;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isLogChat() {
        return logChat;
    }

    public void setLogChat(boolean logChat) {
        this.logChat = logChat;
    }
}
