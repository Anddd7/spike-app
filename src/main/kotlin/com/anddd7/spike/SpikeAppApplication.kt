package com.anddd7.spike

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpikeAppApplication

fun main(args: Array<String>) {
  runApplication<SpikeAppApplication>(*args)
}
