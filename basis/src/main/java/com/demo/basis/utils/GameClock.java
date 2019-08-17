package com.demo.basis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;

/**
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 11:48
 */
public class GameClock {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameClock.class);
    private static Clock clock = Clock.systemDefaultZone();

    public static long millis() {
        return clock.millis();
    }

    public static int seconds() {
        return (int) (millis() / 1000);
    }
}
