package com.dh.im.constants;

import com.dh.im.session.Session;

import io.netty.util.AttributeKey;

/**
 * 为登录成功绑定标志位
 * 
 * @author Lenovo
 *
 */
public interface Attributes {
	AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
	AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}