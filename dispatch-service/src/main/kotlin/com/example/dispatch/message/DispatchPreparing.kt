package com.example.dispatch.message

import com.example.dispatch.util.NoArgConstructor
import java.util.UUID

@NoArgConstructor
class DispatchPreparing (
    val orderId: UUID
)