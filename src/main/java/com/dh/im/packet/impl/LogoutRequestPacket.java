package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestPacket extends Packet {
	@Override
	public Byte getCommand() {

		return CommandEnum.LOGOUT_REQUEST;
	}
}