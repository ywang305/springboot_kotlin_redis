package io.rest.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KbApplication

fun main(args: Array<String>) {
    runApplication<KbApplication>(args = *args)
}
