package com.dh.im.console.impl;

import java.util.Scanner;

import com.dh.im.console.ConsoleCommand;
import com.dh.im.packet.impl.MessageRequestPacket;

import io.netty.channel.Channel;

public class SendToUserConsoleCommand implements ConsoleCommand {
	@Override
	public void exec(Scanner scanner, Channel channel) {
		System.out.print("发送消息给某个某个用户：");

		String toUserId = scanner.next();
		String message = scanner.next();
		channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
	}
}