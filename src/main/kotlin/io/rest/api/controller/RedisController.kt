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

    @GetMapping
    fun get(id: Long?): Any? {
<<<<<<< HEAD
        val isExist = redisTemplate.opsForHash<String,JobVar>().hasKey(id, "jobvar")
        return if(isExist)
            redisTemplate.opsForHash<Any,Any>().get(id, "jobvar")
=======
        val isExist = redisTemplate.opsForHash<String,JobVar>().hasKey(id, "jobVar")
        return if(isExist)
            redisTemplate.opsForHash<Any,Any>().get(id, "jobVar")
>>>>>>> 63292a216fb02aa980a49768f5064aa042462ad2
            else null
    }

    @PostMapping
    fun post(@RequestBody jobVar: JobVar): ResponseEntity<Any> {
        val uniqueId = atom.incrementAndGet()
<<<<<<< HEAD
        redisTemplate.opsForHash<String,JobVar>().put(uniqueId, "jobvar", jobVar)
=======
        redisTemplate.opsForHash<String,JobVar>().put(uniqueId, "jobVar", jobVar)
>>>>>>> 63292a216fb02aa980a49768f5064aa042462ad2
        return ResponseEntity.ok().body(object{ val id = uniqueId})
    }
}

data class JobVar(val date: String,
                  val time: String,
                  val addressList: List<String>)