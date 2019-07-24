package com.demo.data.server;

/**
 * 平台标识定义
 *
 * @author hank
 * @version 2018/4/13 0013 15:33
 */
public enum Platforms {
    /** 37wan */
    P_37_WAN("31", "37wan", ")8e;R*GL4#),m9jW78ei~VpO243!Sx", "nf3NNs~ip;bv;R~bF1u2q7O@_)miT2", 1),
    ;
    private String gameId;
    private String name;
    private String loginKey;
    private String rechargeKey;
    private byte id;

    Platforms(String gameId, String name, String loginKey, String rechargeKey, int id) {
        this.gameId = gameId;
        this.name = name;
        this.loginKey = loginKey;
        this.rechargeKey = rechargeKey;
        this.id = (byte) id;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public String getRechargeKey() {
        return rechargeKey;
    }

    public String getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public byte getId() {
        return id;
    }

    public static Platforms findByName(String name) {
        for (Platforms platforms : Platforms.values()) {
            if (platforms.name.equals(name)) {
                return platforms;
            }
        }
        return null;
    }

    public static Platforms findByID(byte id) {
        for (Platforms platforms : Platforms.values()) {
            if (platforms.id == id) {
                return platforms;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Platforms{"
                + "gameid='"
                + gameId
                + '\''
                + ", name='"
                + name
                + '\''
                + ", id="
                + id
                + '}';
    }
}
