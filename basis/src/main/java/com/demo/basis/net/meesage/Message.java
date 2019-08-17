package com.demo.basis.net.meesage;

import com.demo.basis.net.MessageFactory;
import com.demo.basis.utils.Consts;
import com.demo.basis.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 消息
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:57
 */
public abstract class Message extends Bean {
    private static final Logger LOGGER = LoggerFactory.getLogger(Message.class);
    /**
     * 消息最大长度
     */
    public static final int MAX_LENGTH = 4 * Consts.MB;
    /**
     * buff初始大小
     */
    public static final int INIT_BUFFER_SIZE = 16 * Consts.KB;

    public abstract int id();

    /**
     * 编码
     *
     * @param message
     * @param out
     * @return
     */
    public static int encode(ByteBuf out, Message message) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(INIT_BUFFER_SIZE);
        try {
            // 写入id
            buf.writeInt(message.id());
            // 写入消息
            message.write(buf);
            if (buf.readableBytes() > MAX_LENGTH) {
                throw new IllegalArgumentException(
                        StringUtils.format("{}消息太长了: {}", message.getClass().getName(), buf.readableBytes()));
            }
            int readableBytes = buf.readableBytes();
            //写入长度
            out.writeInt(readableBytes);
            //写入buf
            out.writeBytes(buf);
            if (buf.readableBytes() > 0) {
                throw new IllegalArgumentException(
                        StringUtils.format("消息没写完,还剩: {}", buf.readableBytes()));
            }
            return readableBytes;


        } catch (UnsupportedEncodingException e) {
            LOGGER.error("{} : {}", message.getClass(), message, e);
            return 0;
        } finally {
            buf.release();
        }
    }

    /**
     * 编码消息
     *
     * @return
     */
    public static byte[] encodeToBytes(Message message) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(INIT_BUFFER_SIZE);
        try {
            encode(buf, message);
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            return bytes;
        } finally {
            buf.release();
        }
    }

    /**
     * 解码消息
     *
     * @param in
     * @param factory
     * @return
     */
    public static Message decode(ByteBuf in, MessageFactory factory) {
        in.markReaderIndex();
        int length = in.readInt();
        if (length < 0) {
            // 缓冲区数据不足
            in.resetReaderIndex();
            return null;
        }
        if (length > MAX_LENGTH) {
            throw new IllegalArgumentException(StringUtils.format("消息太长:{}", length));
        }
        if (length > in.readableBytes()) {
            in.resetReaderIndex();
            return null;
        }
        int id = in.readInt();
        Message message = factory.create(id);
        if (message == null) {
            throw new IllegalArgumentException(StringUtils.format("非法id:{}", id));
        }
        try {
            message.read(in);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("读不出来？", e);
            return null;
        }
        return message;
    }

    public abstract Node from();
    public abstract Node to();

}
