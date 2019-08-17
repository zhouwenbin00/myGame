package com.demo.basis.net;

import com.demo.basis.base.Factory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端初始化器
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:30
 */
public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpServerInitializer.class);
    private final Factory<ByteToMessageHandler> decoderFactory;
    private final Factory<MessageToByteHandler> encoderFactory;
    private final MessageHandler messageHandler;
    private final int timeOutSeconds;

    public TcpServerInitializer(
            Factory<ByteToMessageHandler> decoderFactory,
            Factory<MessageToByteHandler> encoderFactory,
            MessageHandler messageHandler) {
        this(decoderFactory, encoderFactory, messageHandler, 0);
    }

    public TcpServerInitializer(
            Factory<ByteToMessageHandler> decoderFactory,
            Factory<MessageToByteHandler> encoderFactory,
            MessageHandler messageHandler,
            int timeOutSeconds) {
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
        this.messageHandler = messageHandler;
        this.timeOutSeconds = timeOutSeconds;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(decoderFactory.create());
        pipeline.addLast(new ByteArrayEncoder());
        pipeline.addLast(encoderFactory.create());
        if (timeOutSeconds > 0) {
            pipeline.addLast(new ReadTimeoutHandler(timeOutSeconds));
        }
        pipeline.addLast(messageHandler);
        pipeline.addLast(new ExceptionHandler());
    }

}
