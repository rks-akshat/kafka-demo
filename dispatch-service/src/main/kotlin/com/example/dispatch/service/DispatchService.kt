package com.example.dispatch.service

import com.example.dispatch.message.OrderCreated
import com.example.dispatch.message.OrderDispatched
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class DispatchService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.dispatch-topic:order.dispatched}") private val topicName: String
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun process(payload: OrderCreated) {
        kafkaTemplate.send(
            topicName,
            OrderDispatched(payload.id),
        )
        logger.info("Order dispatched message sent id = ${payload.id}")
    }

}