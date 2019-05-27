package com.dh.im.console.impl;

import java.util.Scanner;

import com.dh.im.console.ConsoleCommand;
import com.dh.im.packet.impl.LoginRequestPacket;

import io.netty.channel.Channel;

public class LoginConsoleCommand implements ConsoleCommand {

	@Override
	public void exec(Scanner scanner, Channel channel) {
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

		System.out.print("输入用户名登录: ");
		loginRequestPacket.setUsername(scanner.nextLine());
		loginRequestPacket.setPassword("pwd");

		// 发送登录数据包
		channel.writeAndFlush(loginRequestPacket);
		waitForLoginResponse();
	}

	private static void waitForLoginResponse() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignored) {
		}
	}
}