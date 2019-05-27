package com.dh.im.packet.impl;

import java.util.List;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CommandEnum.CREATE_GROUP_RESPONSE;
    }
}