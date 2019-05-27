package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录返回类
 * 
 * @author Lenovo
 *
 */
@Getter
@Setter
public class LoginResponsePacket extends Packet {
	private boolean success;

	private String reason;
	private String username;
	private String userid;

	@Override
	public Byte getCommand() {
		return CommandEnum.LOGIN_RESPONSE;
	}
}