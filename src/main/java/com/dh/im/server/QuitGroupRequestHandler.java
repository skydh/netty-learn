package com.dh.im.server;

import com.dh.im.packet.impl.QuitGroupRequestPacket;
import com.dh.im.packet.impl.QuitGroupResponsePacket;
import com.dh.im.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) {
		// 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
		String groupId = requestPacket.getGroupId();
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		channelGroup.remove(ctx.channel());

		// 2. 构造退群响应发送给客户端
		QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

		responsePacket.setGroupId(requestPacket.getGroupId());
		responsePacket.setSuccess(true);
		ctx.channel().writeAndFlush(responsePacket);

	}

	// 2. 构造单例
	public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

	protected QuitGroupRequestHandler() {
	}
}