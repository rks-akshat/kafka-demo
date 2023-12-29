package com.example.trackingservice.handler

import com.example.trackingservice.message.DispatchPreparing
import com.example.trackingservice.service.OrderTrackingService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class OrderDispatchedHandler(
    private val orderTrackingService: OrderTrackingService,
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(
        id = "orderDispatchedClient",
        topics = ["dispatch.tracking"],
        groupId = "dispatch.dispatch.tracking.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun handleOrderDispatch(
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: String,
        @Payload dispatchPreparing: DispatchPreparing,
    ) {
        logger.info("Received message: key = $key, partition = $partition, payload = $dispatchPreparing")
        orderTrackingService.process(key, dispatchPreparing)
    }

}