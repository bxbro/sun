package com.bxbro.sun.platform.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 任务类型序列化类
 *
 * @author: dong
 * @date: 2023/2/20 21:03
 * @since: 1.0
 */
public class TaskTypeSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value == null) {
            gen.writeString("未知的任务类型");
            return;
        }
        switch (value) {
            case 0:
                gen.writeString("生活");
                break;
            case 1:
                gen.writeString("工作");
                break;
            default:
                break;
        }
    }
}
