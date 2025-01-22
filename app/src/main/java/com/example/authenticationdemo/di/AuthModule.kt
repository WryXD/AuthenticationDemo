package com.example.authenticationdemo.di

import com.example.authenticationdemo.domain.repository.EmailPasswordAuthRepository
import com.example.authenticationdemo.data.EmailPasswordAuthRepositoryImpl
import com.example.authenticationdemo.domain.use_case.UserLoginUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideEmailPasswordAuthRepository(firebaseAuth: FirebaseAuth): EmailPasswordAuthRepository {
        return EmailPasswordAuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: EmailPasswordAuthRepositoryImpl): UserLoginUseCase {
        return UserLoginUseCase(authRepository)
    }
}