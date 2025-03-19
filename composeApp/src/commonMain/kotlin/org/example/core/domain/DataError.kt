package org.example.core.domain

sealed interface DataError : Error{

    enum class Remote : DataError{
        REQUEST_TIME_OUT,
        NO_INTERNET,
        TOO_MANY_REQUESTS,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : DataError{
        DISK_FULL,
        UNKNOWN
    }
}