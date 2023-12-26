package com.example.dispatch.handler

import com.example.dispatch.message.OrderCreated
import com.example.dispatch.service.DispatchService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderCreatedHandler (
    private val dispatchService: DispatchService,
){

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(
        id = "orderConsumerClient",
        topics = ["order.created"],
        groupId = "dispatch.order.created.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun listen(payload: OrderCreated) {
        logger.info("Received message: payload = id:${payload.id} payload:${payload.payload}")
        try {
            dispatchService.process(payload)
        } catch (e: Exception) {
            logger.error("Error processing payload id = ${payload.id}", e)
        }
    }

}