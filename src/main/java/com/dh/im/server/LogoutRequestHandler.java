package com.dh.im.server;

import com.dh.im.packet.impl.LogoutRequestPacket;
import com.dh.im.packet.impl.LogoutResponsePacket;
import com.dh.im.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
		SessionUtil.unBindSession(ctx.channel());
		LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
		logoutResponsePacket.setSuccess(true);
		ctx.channel().writeAndFlush(logoutResponsePacket);
	}

	// 2. 构造单例
	public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

	protected LogoutRequestHandler() {
	}
}