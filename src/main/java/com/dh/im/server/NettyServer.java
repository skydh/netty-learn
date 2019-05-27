package com.dh.im.server;

import java.util.Date;

import com.dh.im.codec.PacketDecoder;
import com.dh.im.codec.PacketEncoder;
import com.dh.im.codec.Spliter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 服务端启动类
 * 
 * @author Lenovo
 *
 */
public class NettyServer {

	public static void main(String[] args) {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
				.childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					protected void initChannel(NioSocketChannel ch) {

						// 我们这里通过职责连模式开发，一个个接着调用哦
						ch.pipeline().addLast(new IMIdleStateHandler());
						ch.pipeline().addLast(new Spliter());
						ch.pipeline().addLast(new PacketDecoder());
						/**
						 * 上面的也可以改成单例，但是就不写了，意思下
						 */

						// ch.pipeline().addLast(new FirstServerHandler());
						ch.pipeline().addLast(LoginRequestHandler.INSTANCE);

						ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
						// 判断是否登录，这里判断通过后继续吧数据往下放，采用的是所有数据都监控的策略
						ch.pipeline().addLast(AuthHandler.INSTANCE);
						// 后续采用将用户信息存放到缓存里面，每次登陆，判断携带的用户id,也就是token，是否在缓存里面
						ch.pipeline().addLast(MessageRequestHandler.INSTANCE);

						// 创建群请求处理器
						ch.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
						// 加群请求处理器
						ch.pipeline().addLast(JoinGroupRequestHandler.INSTANCE);
						// 退群请求处理器
						ch.pipeline().addLast(QuitGroupRequestHandler.INSTANCE);
						// 获取群成员请求处理器
						ch.pipeline().addLast(ListGroupMembersRequestHandler.INSTANCE);
						ch.pipeline().addLast(GroupMessageRequestHandler.INSTANCE);
						// 登出请求处理器
						ch.pipeline().addLast(LogoutRequestHandler.INSTANCE);
						ch.pipeline().addLast(new PacketEncoder());
					}
				});
		bind(serverBootstrap, 8000);
	}

	private static void bind(final ServerBootstrap serverBootstrap, final int port) {
		serverBootstrap.bind(port).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
			} else {
				System.err.println("端口[" + port + "]绑定失败!");
			}
		});
	}
}
