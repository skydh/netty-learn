package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

/**
 * 消息返回类
 * 
 * @author Lenovo
 *
 */
@Getter
@Setter
public class MessageResponsePacket extends Packet {

	private String message;
	private String fromUserId;
	private String fromUserName;

	@Override
	public Byte getCommand() {

		return CommandEnum.MESSAGE_RESPONSE;
	}
}