package com.dh.im.server;

import com.dh.im.util.LoginUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 将其转换为单例模式
 * 
 * @author Lenovo
 *
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

	// 2. 构造单例
	public static final AuthHandler INSTANCE = new AuthHandler();

	protected AuthHandler() {
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (!LoginUtil.hasLogin(ctx.channel())) {
			ctx.channel().close();
		} else {
			// 一条连接，前面验证过一次就好了，没必要后续的消息都要一直验证，毕竟都是在一条连接上的啊

			ctx.pipeline().remove(this);
			super.channelRead(ctx, msg);
		}
	}
}