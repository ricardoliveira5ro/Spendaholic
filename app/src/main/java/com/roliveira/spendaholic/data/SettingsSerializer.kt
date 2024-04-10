package com.roliveira.spendaholic.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.roliveira.spendaholic.ProtoSettings
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<ProtoSettings> {
    override val defaultValue: ProtoSettings
        get() = ProtoSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoSettings {
        try {
            return ProtoSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ProtoSettings, output: OutputStream) {
        t.writeTo(output)
    }
}