package org.intellibear.quoteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.intellibear.quoteapp.quote.data.repositories.QuoteRepositoryImpl
import org.intellibear.quoteapp.quote.domain.repositories.QuoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsQuoteRepository(impl: QuoteRepositoryImpl): QuoteRepository
}