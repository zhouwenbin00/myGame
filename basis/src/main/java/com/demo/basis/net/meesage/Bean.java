package com.demo.basis.net.meesage;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 11:07
 */
public abstract class Bean {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bean.class);

    public abstract boolean write(ByteBuf buf) throws UnsupportedEncodingException;

    public abstract boolean read(ByteBuf buf) throws UnsupportedEncodingException;
}
