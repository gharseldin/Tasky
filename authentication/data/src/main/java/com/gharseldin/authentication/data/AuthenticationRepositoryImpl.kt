package com.gharseldin.authentication.data

import com.gharseldin.authentication.domain.AuthenticationRepository
import com.gharseldin.core.data.networking.post
import com.gharseldin.core.domain.util.DataError
import com.gharseldin.core.domain.util.EmptyDataResult
import io.ktor.client.HttpClient

class AuthenticationRepositoryImpl(private val httpClient: HttpClient) : AuthenticationRepository {
    override suspend fun register(
        fullName:String,
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                fullName = fullName,
                email = email,
                password = password
            )
        )
    }
}
