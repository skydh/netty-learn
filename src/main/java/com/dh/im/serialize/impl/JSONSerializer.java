package com.dh.im.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.dh.im.constants.SerializerEnum;
import com.dh.im.serialize.Serializer;

/**
 * fastjson算法实现序列化和反序列化
 * 
 * @author Lenovo
 *
 */
public class JSONSerializer implements Serializer {

	@Override
	public byte getSerializerAlgorithm() {

		return SerializerEnum.JSON;
	}

	@Override
	public byte[] serialize(Object object) {

		return JSON.toJSONBytes(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {

		return JSON.parseObject(bytes, clazz);
	}
}