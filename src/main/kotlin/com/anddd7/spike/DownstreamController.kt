package com.anddd7.spike

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject


@RestController
@RequestMapping("/downstream")
class DownstreamController(
  private val restTemplateBuilder: RestTemplateBuilder,
  private val restTemplateInterceptor: RestTemplateInterceptor,
) {
  @Value("\${DOWNSTREAM_URL:http://localhost:8080}")
  lateinit var downstreamUrl: String

  private val restTemplate: RestTemplate by lazy {
    restTemplateBuilder
      .rootUri(downstreamUrl)
      .interceptors(restTemplateInterceptor)
      .build()
  }

  @GetMapping("/{path}")
  fun oneLevelCall(@PathVariable path: String): String {
    return restTemplate.getForObject("/${path}")
  }

  @GetMapping("/downstream/{path}")
  fun twoLevelCall(@PathVariable path: String): String {
    return restTemplate.getForObject("/downstream/${path}")
  }
}


