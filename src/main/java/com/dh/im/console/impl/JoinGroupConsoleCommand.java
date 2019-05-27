package com.dh.im.console.impl;

import java.util.Scanner;

import com.dh.im.console.ConsoleCommand;
import com.dh.im.packet.impl.JoinGroupRequestPacket;

import io.netty.channel.Channel;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}