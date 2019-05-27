package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuitGroupResponsePacket extends Packet {

	private String groupId;

	private boolean success;

	private String reason;

	@Override
	public Byte getCommand() {

		return CommandEnum.QUIT_GROUP_RESPONSE;
	}
}