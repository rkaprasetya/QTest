package com.raka.qtest.domain.usecase

import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.domain.repository.ShopRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val shopRepository: ShopRepository) {
    sealed class Result {
        object Loading : Result()
        data class Success(val products: ProductPromoLocal) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(id: String): Observable<Result> {
        return shopRepository.getProduct(id)
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}