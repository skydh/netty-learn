package com.dh.im.packet.impl;
import com.dh.im.constants.CommandEnum;
import com.dh.im.packet.Packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return CommandEnum.QUIT_GROUP_REQUEST;
    }
}