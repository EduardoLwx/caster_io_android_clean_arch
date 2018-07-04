package com.example.domain.test

import java.util.*

object BaseTypesFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean{
        return Math.random() < 0.5
    }
}