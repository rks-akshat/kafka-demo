package com.example.dispatch.message

import com.example.dispatch.util.NoArgConstructor
import java.util.*

@NoArgConstructor
class OrderCreated(
    val id: UUID,
    val payload: String,
)