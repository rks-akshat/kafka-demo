package com.example.dispatch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class DispatchApplication

fun main(args: Array<String>) {
	runApplication<DispatchApplication>(*args)
}
