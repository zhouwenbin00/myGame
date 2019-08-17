package com.demo.basis.net;

import com.demo.basis.net.meesage.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息工厂
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 11:32
 */
public abstract class MessageFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFactory.class);

    public Message create(int id) {
        try {
            return getClass(id).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("message:{}", id, e);
        }
        return null;
    }

    protected abstract Class<? extends Message> getClass(int id);
}
