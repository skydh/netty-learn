package com.dh.im.server;

import com.dh.im.packet.impl.GroupMessageRequestPacket;
import com.dh.im.packet.impl.GroupMessageResponsePacket;
import com.dh.im.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {
		// 1.拿到 groupId 构造群聊消息的响应
		String groupId = requestPacket.getToGroupId();
		GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
		responsePacket.setFromGroupId(groupId);
		responsePacket.setMessage(requestPacket.getMessage());
		responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

		// 2. 拿到群聊对应的 channelGroup，写到每个客户端
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		channelGroup.writeAndFlush(responsePacket);
	}

	// 2. 构造单例
	public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

	protected GroupMessageRequestHandler() {
	}
}