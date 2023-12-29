package com.example.dispatch.message

import com.example.trackingservice.util.NoArgConstructor
import java.util.UUID

@NoArgConstructor
class DispatchPreparing (
    val orderId: UUID
)