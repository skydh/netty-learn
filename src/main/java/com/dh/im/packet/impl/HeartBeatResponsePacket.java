package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

public class HeartBeatResponsePacket extends Packet {
	@Override
	public Byte getCommand() {
		return CommandEnum.HEARTBEAT_RESPONSE;
	}
}