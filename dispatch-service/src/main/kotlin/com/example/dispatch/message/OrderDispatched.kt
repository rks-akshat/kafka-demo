package com.example.dispatch.message

import com.example.dispatch.util.NoArgConstructor
import java.util.UUID

@NoArgConstructor
class OrderDispatched (
    val orderId: UUID
)