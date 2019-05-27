package com.dh.im.console.impl;

import java.util.Scanner;

import com.dh.im.console.ConsoleCommand;
import com.dh.im.packet.impl.LogoutRequestPacket;

import io.netty.channel.Channel;

public class LogoutConsoleCommand implements ConsoleCommand {
	@Override
	public void exec(Scanner scanner, Channel channel) {
		LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
		channel.writeAndFlush(logoutRequestPacket);
	}
}