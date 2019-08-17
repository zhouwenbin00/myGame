package com.demo.basis.net;

import com.demo.basis.base.Factory;
import com.demo.basis.base.Governor;
import com.demo.basis.net.meesage.Message;
import com.demo.basis.utils.GameClock;
import com.sun.istack.internal.Nullable;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息处理器
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 11:01
 */
public abstract class MessageHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);
    protected final String name;
    public static final ThreadLocal<Set<Channel>> localChannels = new ThreadLocal<>();
    private final AtomicInteger channelCount = new AtomicInteger(0);
    private final AttributeKey<Governor> governorKey;
    private final Factory<Governor> governorFactory;
    private boolean forceCloseOnExceptionCaught = true;

    public MessageHandler(String name, @Nullable Factory<Governor> governorFactory) {
        this(name, governorFactory, true);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        onChannelInactive(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        onChannelActive(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof Message)) {
            return;
        }
        Message message = (Message) msg;
        System.out.println("channelRead");
        System.out.println("message");
        onChannelRead(ctx.channel(), message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }

    public MessageHandler(
            String name,
            Factory<Governor> governorFactory,
            boolean forceCloseOnExceptionCaught) {
        this.name = name;
        this.governorFactory = governorFactory;
        if (this.governorFactory != null) {
            this.governorKey = AttributeKey.valueOf(Governor.class, String.valueOf(GameClock.seconds()));
        } else {
            this.governorKey = null;
        }
        this.forceCloseOnExceptionCaught = forceCloseOnExceptionCaught;
    }


    protected abstract void onChannelActive(Channel channel);

    protected abstract void onChannelInactive(Channel channel);

    protected abstract void onChannelRead(Channel channel, Message msg);
}
