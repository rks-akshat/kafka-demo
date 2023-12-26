package com.example.trackingservice.message

import com.example.trackingservice.util.NoArgConstructor
import java.util.UUID

@NoArgConstructor
class OrderDispatched (
    val orderId: UUID
)