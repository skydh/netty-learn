package com.dh.im.packet;

import lombok.Data;

/**
 * 
 * 魔数，版本号，序列化算法，指令，数据长度，数据
 * 
 * 消息公共类
 * 
 * @author Lenovo
 *
 */
@Data
public abstract class Packet {
	/**
	 * 协议版本
	 */
	private Byte version = 1;

	/**
	 * 指令
	 * 
	 * @return
	 */
	public abstract Byte getCommand();
}