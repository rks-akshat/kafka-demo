package com.example.dispatch.order

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
        groupId = "dispatch.order.created.consumer"
    )
    fun listen(payload: String) {
        logger.info("Received message: payload = $payload")
        dispatchService.process(payload)
    }

}