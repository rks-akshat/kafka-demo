package com.example.trackingservice.service

import com.example.trackingservice.message.OrderDispatched
import com.example.trackingservice.message.TrackingStatusUpdate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderTrackingService (
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @Value("\${kafka.tracking-topic}") private lateinit var trackingTopic: String

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun process(orderDispatched: OrderDispatched) {
        kafkaTemplate.send(
            trackingTopic,
            TrackingStatusUpdate(orderDispatched.orderId, TrackingStatusUpdate.TrackingStatus.PROCESSING)
        )
        logger.info("Processed order dispatched event : id = ${orderDispatched.orderId}")
    }


}