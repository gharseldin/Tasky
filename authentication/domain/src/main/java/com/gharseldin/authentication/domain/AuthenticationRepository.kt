package com.gharseldin.authentication.domain

import com.gharseldin.core.domain.util.DataError
import com.gharseldin.core.domain.util.EmptyDataResult

interface AuthenticationRepository {
    suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network>
}
