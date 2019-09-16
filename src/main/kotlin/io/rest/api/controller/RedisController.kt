package io.rest.api.controller

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping(path = ["/redis"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class RedisController(private val redisTemplate: RedisTemplate<Any, Any>) {
    val atom = AtomicLong()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Any? {
        val isExist = redisTemplate.opsForHash<String,JobVar>().hasKey(id, "jobvar")
        return if(isExist) {
            val result = redisTemplate.opsForHash<Any,Any>().get(id, "jobvar")
            result
        } else null
    }

    @PostMapping
    fun post(@RequestBody jobvar: JobVar): ResponseEntity<Any> {
        val uniqueId = atom.incrementAndGet()
        redisTemplate.opsForHash<String,JobVar>().put(uniqueId, "jobvar", jobvar)
        return ResponseEntity.ok().body(object{ val id = uniqueId})
    }
}

data class JobVar(val date: String,
                  val time: String,
                  val addressList: List<String>)
