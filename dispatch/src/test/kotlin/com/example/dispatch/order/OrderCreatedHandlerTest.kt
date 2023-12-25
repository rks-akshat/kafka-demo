package com.example.dispatch.order

import com.example.dispatch.service.DispatchService
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired

internal class OrderCreatedHandlerTest {

    private lateinit var dispatchServiceMock: DispatchService
    private lateinit var orderCreatedHandler: OrderCreatedHandler

    @BeforeEach
    fun setUp() {
        dispatchServiceMock = mock(DispatchService::class.java)
        orderCreatedHandler = OrderCreatedHandler(dispatchServiceMock)
    }

    @Test
    fun listen() {
        orderCreatedHandler.listen("payload")
        verify(dispatchServiceMock, times(1)).process("payload")
    }
}