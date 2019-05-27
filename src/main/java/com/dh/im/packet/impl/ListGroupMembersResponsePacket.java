package com.dh.im.packet.impl;
import java.util.List;

import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;
import com.dh.im.session.Session;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return CommandEnum.LIST_GROUP_MEMBERS_RESPONSE;
    }
}