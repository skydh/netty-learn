package com.dh.im.console;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * 控制台基本命令
 * 
 * @author Lenovo
 *
 */
public interface ConsoleCommand {
	void exec(Scanner scanner, Channel channel);
}