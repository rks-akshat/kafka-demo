package com.example.dispatch.service

import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DispatchServiceTest {

    private lateinit var dispatchService: DispatchService

    @BeforeEach
    fun setUp() {
        dispatchService = DispatchService()
    }

    @Test
    fun process() {
        dispatchService.process("payload")
    }
}