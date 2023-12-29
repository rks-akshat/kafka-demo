package com.example.dispatch.message

import com.example.dispatch.util.NoArgConstructor
import java.util.*

@NoArgConstructor
class DispatchCompleted (
    val orderId: UUID,
)