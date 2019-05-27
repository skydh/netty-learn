package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

/**
 * 消息发送类
 * 
 * @author Lenovo
 *
 */
@Getter
@Setter
public class MessageRequestPacket extends Packet {

	private String message;
	private String toUserId;

	public MessageRequestPacket(String toUserId, String message) {
		this.message = message;
		this.toUserId = toUserId;
	}

	@Override
	public Byte getCommand() {
		return CommandEnum.MESSAGE_REQUEST;
	}
}