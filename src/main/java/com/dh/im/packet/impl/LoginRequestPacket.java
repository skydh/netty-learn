package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求包
 * 
 * @author Lenovo
 *
 */
@Getter
@Setter
public class LoginRequestPacket extends Packet {
	private String userId;

	private String username;

	private String password;

	@Override
	public Byte getCommand() {

		return CommandEnum.LOGIN_REQUEST;
	}
}