package com.lelestacia.transactionmanagement.di

import android.content.Context
import com.lelestacia.transactionmanagement.domain.validator.BuyerNameValidator
import com.lelestacia.transactionmanagement.domain.validator.DateValidator
import com.lelestacia.transactionmanagement.domain.validator.PhoneNumberValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ValidatorModule {

    @Provides
    @ViewModelScoped
    fun provideDateValidator(
        @ApplicationContext context: Context
    ): DateValidator = DateValidator(context)

    @Provides
    @ViewModelScoped
    fun provideBuyerNameValidator(
        @ApplicationContext context: Context
    ): BuyerNameValidator = BuyerNameValidator(context)

    @Provides
    @ViewModelScoped
    fun providePhoneNumberValidator(
        @ApplicationContext context: Context
    ): PhoneNumberValidator = PhoneNumberValidator(context)
}