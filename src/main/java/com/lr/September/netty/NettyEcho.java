package com.lr.September.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * 基于Netty实现了echo程序服务端
 *
 * 首先创建了一个事件处理器（等同于Reactor模式中的事
 * 件处理器），然后创建了bossGroup和workerGroup，再之后创建并初始化了ServerBootstrap
 *
 * @author shijie.xu
 * @since 2019年09月02日
 */
public class NettyEcho {
    /**
     * 事件处理器
     */
    final EchoServerHandler echoServerHandler = new EchoServerHandler();
    /**
     * boss线程组
     */
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    /**
     * woker线程组
     */
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void dosth() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(echoServerHandler);
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(9090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            终⽌⼯作线程组
            workerGroup.shutdownGracefully();
//            终⽌boss线程组
            bossGroup.shutdownGracefully();
        }
    }

}
