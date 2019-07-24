package com.demo.data.server;

import org.apache.commons.lang3.Validate;

/** @Auther: zhouwenbin @Date: 2019/7/24 19:42 */
public interface ServerKey {
    /** 区服最大 */
    int SERVER_MAX = 0xFFFF;
    /** 区服最小 */
    int SERVER_MIN = 1;
    /** 平台最小 */
    byte PLATFORM_MIN = 1;

    int serverKey();

    static int serverKey(int server, byte platform) {
        Validate.isTrue(
                server >= SERVER_MIN && server <= SERVER_MAX,
                "%s<=server<=%s,yours:%s",
                SERVER_MIN,
                SERVER_MAX,
                server);
        Validate.isTrue(platform >= PLATFORM_MIN, "platform>=%s,yours:%s", PLATFORM_MIN, platform);
        return ((platform & 0XFF) << 16) | ((server & 0xFFFF));
    }

    static int server(ServerKey key) {
        return server(key.serverKey());
    }

    static byte platform(ServerKey key) {
        return platform(key.serverKey());
    }

    static int server(int key) {
        return key & 0X00FFFF;
    }

    static byte platform(int key) {
        return (byte) (key >> 16);
    }

    static int lianFuServerKey() {
        return -1;
    }
}
