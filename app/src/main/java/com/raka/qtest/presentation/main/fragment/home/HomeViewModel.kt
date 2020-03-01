package com.raka.qtest.presentation.main.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableArrayList
import com.raka.qtest.data.model.compact.CategoryCompact
import com.raka.qtest.data.model.compact.ProductCompact
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.domain.usecase.CategoryUsecase
import com.raka.qtest.domain.usecase.ProductsListUseCase
import com.raka.qtest.domain.usecase.ProductsListUseCase.Result
import com.raka.qtest.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRouter: HomeRouter,
    private val productsListUseCase: ProductsListUseCase,
    private val categoryUsecase: CategoryUsecase
) {
    private val disposables = CompositeDisposable()
    val productsList = ObservableArrayList<ProductCompact>()
    val categoryList = ObservableArrayList<CategoryCompact>()
    private val showErrorGettingData = StickyAction<Boolean>()

    fun bound() {
        productsListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleGetProductListResult(it) }.let {
                disposables.add(it)
            }
    }

    private fun handleGetProductListResult(result: Result) {
        when (result) {
            is Result.Success -> productsList.addAll(result.products)
            is Result.Failure -> showErrorGettingData.trigger(true)
        }
    }

    fun unBound() {
        disposables.clear()
    }

    fun onProductClicked(product: Any) {
        homeRouter.navigate(HomeRouter.Route.DETAIL_PRODUCT, Bundle().apply {
            putString("id", (product as ProductPromoLocal).id)
        })
    }

    fun onLikeClicked(product: Any) {
        productsListUseCase.updateLike(product as ProductCompact)
        getUpdatedList()
    }

    private fun getUpdatedList() {
        productsListUseCase.getUpdatedList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleGetProductListResult(it) }.let {
                disposables.add(it)
            }
    }
}