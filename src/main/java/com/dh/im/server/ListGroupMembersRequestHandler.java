package com.dh.im.server;

import java.util.ArrayList;
import java.util.List;

import com.dh.im.packet.impl.ListGroupMembersRequestPacket;
import com.dh.im.packet.impl.ListGroupMembersResponsePacket;
import com.dh.im.session.Session;
import com.dh.im.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) {
		// 1. 获取群的 ChannelGroup
		String groupId = requestPacket.getGroupId();
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

		// 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
		List<Session> sessionList = new ArrayList<>();
		for (Channel channel : channelGroup) {
			Session session = SessionUtil.getSession(channel);
			sessionList.add(session);
		}

		// 3. 构建获取成员列表响应写回到客户端
		ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

		responsePacket.setGroupId(groupId);
		responsePacket.setSessionList(sessionList);
		ctx.channel().writeAndFlush(responsePacket);
	}

	// 2. 构造单例
	public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

	protected ListGroupMembersRequestHandler() {
	}
}