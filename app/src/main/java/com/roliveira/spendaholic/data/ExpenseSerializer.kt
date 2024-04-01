package com.roliveira.spendaholic.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.roliveira.spendaholic.ProtoExpenseItems
import java.io.InputStream
import java.io.OutputStream

object ExpenseSerializer : Serializer<ProtoExpenseItems> {
    override val defaultValue: ProtoExpenseItems
        get() = ProtoExpenseItems.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoExpenseItems {
        try {
            return ProtoExpenseItems.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ProtoExpenseItems, output: OutputStream) {
        t.writeTo(output)
    }
}