package com.example.trackingservice.message

import java.util.*

class TrackingStatusUpdate (
    val orderId: UUID,
    val trackingStatus: TrackingStatus,
) {

    enum class TrackingStatus {
        PROCESSING, DISPATCHED, RECEIVED
    }

}


