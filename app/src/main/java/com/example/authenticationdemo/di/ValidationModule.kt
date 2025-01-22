package com.example.authenticationdemo.di

import com.example.authenticationdemo.domain.use_case.EmailValidation
import com.example.authenticationdemo.domain.use_case.LoginPasswordValidation
import com.example.authenticationdemo.domain.use_case.LoginValidationUseCase
import com.example.authenticationdemo.domain.use_case.RegisterConfirmPasswordValidation
import com.example.authenticationdemo.domain.use_case.RegisterPasswordValidation
import com.example.authenticationdemo.domain.use_case.RegisterValidationUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {

    @Provides
    @Singleton
    fun provideLoginValidation(
        emailValidation: EmailValidation,
        passwordValidation: LoginPasswordValidation
    ): LoginValidationUseCase {
        return LoginValidationUseCase(emailValidation,passwordValidation)
    }

    @Provides
    @Singleton
    fun provideRegisterValidation(
        emailValidation: EmailValidation,
        passwordValidation: RegisterPasswordValidation,
        confirmPasswordValidation: RegisterConfirmPasswordValidation
    ): RegisterValidationUserCase {
        return RegisterValidationUserCase(emailValidation, passwordValidation, confirmPasswordValidation)
    }

    @Provides
    @Singleton
    fun provideEmailValidation(): EmailValidation {
        return EmailValidation()
    }

    @Provides
    @Singleton
    fun providePasswordValidation(): RegisterPasswordValidation {
        return RegisterPasswordValidation()
    }

    @Provides
    @Singleton
    fun provideConfirmPasswordValidation(): RegisterConfirmPasswordValidation {
        return RegisterConfirmPasswordValidation()
    }

    @Provides
    @Singleton
    fun provideLoginPasswordValidation(): LoginPasswordValidation {
        return LoginPasswordValidation()
    }
}