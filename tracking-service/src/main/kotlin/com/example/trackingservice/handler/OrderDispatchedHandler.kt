package com.example.trackingservice.handler

import com.example.trackingservice.message.DispatchPreparing
import com.example.trackingservice.service.OrderTrackingService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderDispatchedHandler(
    private val orderTrackingService: OrderTrackingService,
) {

    @KafkaListener(
        id = "orderDispatchedClient",
        topics = ["dispatch.tracking"],
        groupId = "dispatch.dispatch.tracking.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun handleOrderDispatch(dispatchPreparing: DispatchPreparing) {
        orderTrackingService.process(dispatchPreparing)
    }

}