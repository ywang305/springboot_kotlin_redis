package io.rest.api.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import java.text.SimpleDateFormat

@Configuration
class AppConfig(private val factory: RedisConnectionFactory) {
    @Bean
    fun objectMapper() = jacksonObjectMapper().apply{
        configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        dateFormat = SimpleDateFormat("MMM dd, yyyy h:mm:ss a")
    }

    @Bean
    fun redisTemplate(om: ObjectMapper): RedisTemplate<Any, Any> {
        val jackson = Jackson2JsonRedisSerializer(Any::class.java)
        jackson.setObjectMapper(om)
        return RedisTemplate<Any, Any>().apply{
            connectionFactory = factory
            keySerializer = jackson
            valueSerializer = jackson
            hashKeySerializer = jackson
            hashValueSerializer = jackson
        }
    }
}