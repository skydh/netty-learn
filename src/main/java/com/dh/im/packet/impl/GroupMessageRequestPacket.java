package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMessageRequestPacket extends Packet {
	private String toGroupId;
	private String message;

	public GroupMessageRequestPacket(String toGroupId, String message) {
		this.toGroupId = toGroupId;
		this.message = message;
	}

	@Override
	public Byte getCommand() {
		return CommandEnum.GROUP_MESSAGE_REQUEST;
	}
}