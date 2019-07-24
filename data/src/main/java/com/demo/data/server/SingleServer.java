package com.demo.data.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/** @Auther: zhouwenbin @Date: 2019/7/24 19:45 */
public class SingleServer implements ServerKey {

    private static Logger LOGGER = LoggerFactory.getLogger(SingleServer.class);
    private int server; // 区服
    private String platform; // 平台
    private String open; // 开服时间
    private String join; // 合服时间
    private String sub; // 子服务器

    private int groupID; // 分组ID
    private LocalDateTime openDate; // 服务器开服时间
    private LocalDateTime joinDate; // 合服时间
    private Set<Integer> subs; // 子服务器ID(包括自己)
    private Platforms platformID; // 平台ID

    private final transient AtomicInteger robotCount = new AtomicInteger();
    private final transient AtomicInteger hangUpCount = new AtomicInteger();

    @Override
    public int serverKey() {
        return ServerKey.serverKey(server, platformID.getId());
    }

    public int getServer() {
        return server;
    }
}
