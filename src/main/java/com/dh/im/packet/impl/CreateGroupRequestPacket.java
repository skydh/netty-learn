package com.dh.im.packet.impl;

import java.util.List;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequestPacket extends Packet {
	private List<String> userIdList;

	@Override
	public Byte getCommand() {
		// TODO Auto-generated method stub
		return CommandEnum.CREATE_GROUP_REQUEST;
	}
}