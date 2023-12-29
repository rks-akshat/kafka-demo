package com.example.trackingservice.service

import com.example.dispatch.message.DispatchCompleted
import com.example.dispatch.message.DispatchPreparing
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

    fun process(key: String, dispatchPreparing: DispatchPreparing) {
        kafkaTemplate.send(
            trackingTopic,
            key,
            TrackingStatusUpdate(dispatchPreparing.orderId, TrackingStatusUpdate.TrackingStatus.PROCESSING)
        )
        logger.info("Processed order dispatched event : id = ${dispatchPreparing.orderId}")
    }

    fun process(key: String, dispatchCompleted: DispatchCompleted) {
        kafkaTemplate.send(
            trackingTopic,
            key,
            TrackingStatusUpdate(dispatchCompleted.orderId, TrackingStatusUpdate.TrackingStatus.COMPLETED)
        )
        logger.info("Processed order dispatched event : id = ${dispatchCompleted.orderId}")
    }


}