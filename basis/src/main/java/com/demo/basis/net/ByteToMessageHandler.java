package com.demo.basis.net;

import com.demo.basis.net.meesage.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 解码器
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:56
 */
public class ByteToMessageHandler extends ByteToMessageDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ByteToMessageHandler.class);
    private final MessageFactory factory;

    public ByteToMessageHandler(MessageFactory messageFactory) {
        this.factory = messageFactory;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Message message;
        while ((message = Message.decode(in, factory))!=null){
            out.add(message);
        }
    }
}
