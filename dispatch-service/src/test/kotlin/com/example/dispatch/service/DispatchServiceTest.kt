package com.example.dispatch.service

import com.example.dispatch.message.OrderCreated
import com.example.dispatch.message.OrderDispatched
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import java.util.*
import java.util.concurrent.CompletableFuture

internal class DispatchServiceTest {

    private lateinit var dispatchService: DispatchService
    private lateinit var kafkaTemplate: KafkaTemplate<String, Any>


    @BeforeEach
    fun setUp() {
        kafkaTemplate = mock()
        dispatchService = DispatchService(kafkaTemplate, "order.dispatched") // why doesn't the @Value annotation work with the test?
    }

    @Test
    fun process() {
        `when`(kafkaTemplate.send(anyString(), any(OrderCreated::class.java))).then { mock(CompletableFuture::class.java) }
        val message = OrderCreated(UUID.randomUUID(), "payload")
        dispatchService.process(message)
        verify(kafkaTemplate, times(1)).send(eq("order.dispatched"), any(OrderDispatched::class.java))
    }
}