package com.example.dispatch.handler

import com.example.dispatch.message.OrderCreated
import com.example.dispatch.service.DispatchService
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

internal class DispatchPreparingHandlerTest {

    private lateinit var dispatchServiceMock: DispatchService
    private lateinit var dispatchPreparingHandler: DispatchPreparingHandler

    @BeforeEach
    fun setUp() {
        dispatchServiceMock = mock(DispatchService::class.java)
        dispatchPreparingHandler = DispatchPreparingHandler(dispatchServiceMock)
    }

    @Test
    fun `can listen to order created events successfully`() {
        val message = OrderCreated(UUID.randomUUID(), "payload")
        dispatchPreparingHandler.listen(message)
        verify(dispatchServiceMock, times(1)).process(message)
    }

    // TODO: write tests for error cases also

}