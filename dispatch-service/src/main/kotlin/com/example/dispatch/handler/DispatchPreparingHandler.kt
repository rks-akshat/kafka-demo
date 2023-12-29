package com.example.dispatch.handler

import com.example.dispatch.message.OrderCreated
import com.example.dispatch.service.DispatchService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class DispatchPreparingHandler (
    private val dispatchService: DispatchService,
){

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(
        id = "orderConsumerClient",
        topics = ["order.created"],
        groupId = "dispatch.order.created.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun listen(
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Payload payload: OrderCreated
    ) {
        logger.info("Received message: payload = key: $key, partition: $partition, payload:${payload.payload}")
        try {
            dispatchService.process(key, payload)
        } catch (e: Exception) {
            logger.error("Error processing payload id = ${payload.id}", e)
        }
    }

}