package com.raka.qtest.domain.usecase

import com.raka.qtest.data.model.compact.CategoryCompact
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.domain.repository.ShopRepository
import io.reactivex.Observable
import javax.inject.Inject

class CategoryUsecase @Inject constructor(private val shopRepository: ShopRepository) {
    sealed class Result {
        object Loading : Result()
        data class Success(val products: List<CategoryCompact>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(id: String): Observable<Result> {
        return shopRepository.getCategoryList()
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}