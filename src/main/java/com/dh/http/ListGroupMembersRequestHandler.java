package com.dh.http;

import java.net.URI;
import java.net.URISyntaxException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<HttpRequest> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpRequest req) throws URISyntaxException {
		
		

		URI uri = new URI(req.uri());
		// 使用浏览器访问localhost:8899会发送二次请求，其中有一次是localhost:8899/favicon.ico,这个url请求访问网站的图标
		if ("/favicon.ico".equals(uri.getPath())) {
			System.out.println("请求favicon.ico");
			return;
		}
		// 向客户端返回的内容
		ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

		response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
		response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

		ctx.writeAndFlush(response);

		// 其实更合理的close连接应该判断是http1.O还是1.1来进行判断请求超时时间来断开channel连接。
		ctx.channel().close();
	}

}