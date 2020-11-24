package com.anddd7.spike

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class HelloController {
  @Value("\${SERVICE_NAME:spike-app-default}")
  lateinit var serviceName: String

  private val serialId = UUID.randomUUID()

  @GetMapping("/ping")
  fun ping(): String = "pong"

  @GetMapping("/serial-id")
  fun serialId(): String = "$serviceName: $serialId"
}
