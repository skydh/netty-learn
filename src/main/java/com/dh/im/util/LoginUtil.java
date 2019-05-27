package com.dh.im.util;

import com.dh.im.constants.Attributes;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {

	/**
	 * 为Channel设置值，设定为true
	 * 
	 * @param channel
	 */
	public static void markAsLogin(Channel channel) {
		channel.attr(Attributes.LOGIN).set(true);
	}

	/**
	 * 判断是否登录
	 * 
	 * @param channel
	 * @return
	 */
	public static boolean hasLogin(Channel channel) {

		Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
		return loginAttr.get() != null;

	}
}