package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;
import com.dh.im.session.Session;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return CommandEnum.GROUP_MESSAGE_RESPONSE;
    }
}