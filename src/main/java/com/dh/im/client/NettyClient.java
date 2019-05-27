package com.dh.im.client;

import java.util.Scanner;

import com.dh.im.codec.PacketDecoder;
import com.dh.im.codec.PacketEncoder;
import com.dh.im.codec.Spliter;
import com.dh.im.console.impl.ConsoleCommandManager;
import com.dh.im.console.impl.LoginConsoleCommand;
import com.dh.im.util.SessionUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端启动
 * 
 * @author Lenovo
 *
 */
public class NettyClient {
	public static void main(String[] args) {

		Bootstrap bootstrap = new Bootstrap();
		NioEventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				// ch.pipeline().addLast(new LifeCyCleTestHandler());
				ch.pipeline().addLast(new Spliter());
				ch.pipeline().addLast(new PacketDecoder());
				// 登录响应处理器
				ch.pipeline().addLast(new LoginResponseHandler());
				// 加个心跳，也就是每隔多少秒向服务端发送一个请求包（定时器）
				ch.pipeline().addLast(new HeartBeatTimerHandler());

				// 收消息处理器
				ch.pipeline().addLast(new MessageResponseHandler());
				// 创建群响应处理器
				ch.pipeline().addLast(new CreateGroupResponseHandler());
				// 加群响应处理器
				ch.pipeline().addLast(new JoinGroupResponseHandler());
				// 退群响应处理器
				ch.pipeline().addLast(new QuitGroupResponseHandler());
				// 获取群成员响应处理器
				ch.pipeline().addLast(new ListGroupMembersResponseHandler());
				// 登出响应处理器
				ch.pipeline().addLast(new LogoutResponseHandler());
				// 群消息响应
				ch.pipeline().addLast(new GroupMessageResponseHandler());

				// ch.pipeline().addLast(new FirstClientHandler());

				ch.pipeline().addLast(new PacketEncoder());
			}
		});

		connect(bootstrap, "127.0.0.1", 8000);
	}

	public static void connect(Bootstrap bootstrap, String host, int port) {
		bootstrap.connect(host, port).addListener(future -> {
			if (future.isSuccess()) {

				System.out.println("连接成功!");
				/**
				 * 启动一个线程来联系
				 */
				startConsoleThread(((ChannelFuture) future).channel());
			} else {
				System.err.println("连接失败，开始重连");
				connect(bootstrap, host, port);

			}
		});

	}

	private static void startConsoleThread(Channel channel) {
		ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
		LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
		Scanner scanner = new Scanner(System.in);

		new Thread(() -> {
			while (!Thread.interrupted()) {
				if (!SessionUtil.hasLogin(channel)) {
					loginConsoleCommand.exec(scanner, channel);
				} else {
					consoleCommandManager.exec(scanner, channel);
				}
			}
		}).start();
	}
}
