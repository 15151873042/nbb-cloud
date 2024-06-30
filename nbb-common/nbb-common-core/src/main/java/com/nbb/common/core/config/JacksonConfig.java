package com.nbb.common.core.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeObjectMapper() {
        return jacksonObjectMapperBuilder -> {
            // 设置LocalDateTime序列化方式
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

            // 设置Date序列化方式
            jacksonObjectMapperBuilder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            jacksonObjectMapperBuilder.timeZone("GMT+8");

            // null值不参与序列化
            jacksonObjectMapperBuilder.serializationInclusion(Include.NON_NULL);
        };
    }
}
