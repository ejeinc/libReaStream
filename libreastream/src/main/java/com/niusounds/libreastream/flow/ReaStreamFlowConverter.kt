package com.niusounds.libreastream.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ReaStreamFlowConverter(
    private val identifier: String,
    private val receiver: PacketReceiver,
) {
    fun receive(): Flow<ReaStreamPacket> = flow {
        emitAll(receiver.receive()
            .map {
                ByteBufferReaStreamPacket(it)
            }
            .filter { it.identifier == identifier }
        )
    }
}
