package com.dh.im.packet.impl;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return CommandEnum.LIST_GROUP_MEMBERS_REQUEST;
    }
}