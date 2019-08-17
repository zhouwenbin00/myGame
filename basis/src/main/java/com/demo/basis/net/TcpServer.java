package com.demo.basis.net;

import com.demo.basis.concurrent.ThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * tcp服务器
 *
 * @Auther: zhouwenbin
 * @Date: 2019/8/17 10:22
 */
public class TcpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);

    private final EventLoopGroup bossGroup =
            new NioEventLoopGroup(1, new ThreadFactory("server-boss", Thread.MAX_PRIORITY));
    private final EventLoopGroup workerGroup =
            new NioEventLoopGroup(4, new ThreadFactory("server-worker", Thread.MAX_PRIORITY));
    private final ServerBootstrap bootstrap;
    private final int port;
    private final String name;

    public TcpServer(String name,
                     int port,
                     ChannelInitializer<SocketChannel> initializer) {
        this.name = name;
        this.port = port;
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(initializer);
    }

    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public void startup(final Consumer<Throwable> onFail) {
        ChannelFuture sync = null;
        try {
            sync = bootstrap.bind(port).addListener(future -> {
                if (!future.isSuccess()) {
                    if (onFail != null) {
                        onFail.accept(future.cause());
                    }
                } else {
                    LOGGER.info("{} : {} start listen", name, port);
                }
            });
        } catch (Exception e) {
            if (onFail != null) {
                onFail.accept(e);
            }
        }

    }

    public void shutdown() throws InterruptedException {
        bossGroup.shutdownGracefully().sync();
        workerGroup.shutdownGracefully().sync();
        LOGGER.info("{} : {} is closed", name, port);
    }
}
