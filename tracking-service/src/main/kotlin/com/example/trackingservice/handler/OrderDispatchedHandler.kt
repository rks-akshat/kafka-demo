package com.example.trackingservice.handler

import com.example.trackingservice.message.OrderDispatched
import com.example.trackingservice.service.OrderTrackingService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderDispatchedHandler(
    private val orderTrackingService: OrderTrackingService,
) {

    @KafkaListener(
        id = "orderDispatchedClient",
        topics = ["order.dispatched"],
        groupId = "dispatch.order.dispatched.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun handleOrderDispatch(orderDispatched: OrderDispatched) {
        orderTrackingService.process(orderDispatched)
    }

}