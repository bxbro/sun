package com.bxbro.sun.platform.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 任务状态序列化类
 *
 * @author: dong
 * @date: 2023/2/20 21:11
 * @since: 1.0
 */
public class TaskStatusSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeString("未知的任务状态");
            return;
        }
        switch (value) {
            case 0:
                gen.writeString("待完成");
                break;
            case 1:
                gen.writeString("已完成");
                break;
            case 2:
                gen.writeString("已延期");
                break;
            default:
                break;
        }
    }
}
