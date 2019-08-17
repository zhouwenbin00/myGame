package com.demo.basis.net;

import com.demo.basis.net.meesage.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码器
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:57
 */
public class MessageToByteHandler extends MessageToByteEncoder<Message> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageToByteHandler.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        Message.encode(out, message);
    }
}
