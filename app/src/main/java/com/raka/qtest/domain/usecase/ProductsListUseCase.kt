package com.raka.qtest.domain.usecase

import com.raka.qtest.data.model.compact.ProductCompact
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.domain.repository.ShopRepository
import io.reactivex.Observable
import javax.inject.Inject

class ProductsListUseCase @Inject constructor(private val shopRepository: ShopRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val products: List<ProductCompact>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(): Observable<Result>{
        return shopRepository.getProductList()
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }

    fun updateLike(productCompact: ProductCompact){
        val like = if(productCompact.liked == 1) 0 else 1
        shopRepository.updateLikedProduct(like,productCompact.id!!)
    }

    fun getUpdatedList():Observable<Result>{
        return shopRepository.getProductListFromDb().toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }

}