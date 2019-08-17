package com.demo.basis.concurrent;

import com.demo.basis.utils.StringUtils;
import io.netty.util.concurrent.FastThreadLocalThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:42
 */
public class ThreadFactory implements java.util.concurrent.ThreadFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadFactory.class);

    private final String name;
    private final int priority;
    private final AtomicInteger index;

    public ThreadFactory(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.index = new AtomicInteger();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new FastThreadLocalThread(r, StringUtils.format("{@}-{@}", name, index.getAndIncrement()));
        thread.setPriority(priority);
        return thread;
    }
}
