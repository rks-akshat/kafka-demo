package com.example.dispatch.service

import com.example.dispatch.handler.OrderCreatedHandler
import com.example.dispatch.message.OrderCreated
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class DispatchServiceTest {

    private lateinit var dispatchService: DispatchService

    @BeforeEach
    fun setUp() {
        dispatchService = DispatchService()
    }

    @Test
    fun process() {
        val message = OrderCreated(UUID.randomUUID(), "payload")
        dispatchService.process(message)
    }
}