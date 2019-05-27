package com.dh.im.server;

import java.util.Date;
import java.util.UUID;

import com.dh.im.packet.impl.LoginRequestPacket;
import com.dh.im.packet.impl.LoginResponsePacket;
import com.dh.im.session.Session;
import com.dh.im.util.LoginUtil;
import com.dh.im.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
		System.out.println(new Date() + ": 收到客户端登录请求……");

		LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
		loginResponsePacket.setVersion(loginRequestPacket.getVersion());
		loginResponsePacket.setUsername(loginRequestPacket.getUsername());

		if (valid(loginRequestPacket)) {
			loginResponsePacket.setSuccess(true);
			String userId = randomUserId();
			loginResponsePacket.setUserid(userId);
			LoginUtil.markAsLogin(ctx.channel());
			System.out.println(new Date() + ": 登录成功!");
			SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
		} else {
			loginResponsePacket.setReason("账号密码校验失败");
			loginResponsePacket.setSuccess(false);
			System.out.println(new Date() + ": 登录失败!");
		}

		// 登录响应
		ctx.channel().writeAndFlush(loginResponsePacket);
	}

	private static String randomUserId() {
		return UUID.randomUUID().toString().split("-")[0];
	}

	private boolean valid(LoginRequestPacket loginRequestPacket) {
		return true;
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		SessionUtil.unBindSession(ctx.channel());
	}

	// 2. 构造单例
	public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

	protected LoginRequestHandler() {
	}
}