package com.example.dispatch.message

import com.example.trackingservice.util.NoArgConstructor
import java.util.*

@NoArgConstructor
class DispatchCompleted (
    val orderId: UUID,
)