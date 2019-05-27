package com.dh.im.server;

import com.dh.im.packet.impl.MessageRequestPacket;
import com.dh.im.packet.impl.MessageResponsePacket;
import com.dh.im.session.Session;
import com.dh.im.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
		// 1.拿到消息发送方的会话信息
		Session session = SessionUtil.getSession(ctx.channel());

		// 2.通过消息发送方的会话信息构造要发送的消息
		MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
		messageResponsePacket.setFromUserId(session.getUserId());
		messageResponsePacket.setFromUserName(session.getUserName());
		messageResponsePacket.setMessage(messageRequestPacket.getMessage());

		// 3.拿到消息接收方的 channel
		Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

		// 4.将消息发送给消息接收方
		if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
			toUserChannel.writeAndFlush(messageResponsePacket);
		} else {
			System.err.println("[" + session.getUserId() + "] 不在线，发送失败!");
		}
	}
	// 2. 构造单例
	public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

	protected MessageRequestHandler() {
	}
}