package com.raka.qtest.data.db

import androidx.room.*
import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.local.ProductPromoLocal
import io.reactivex.Single

@Dao
interface ParametersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertCategory(data: List<CategoryLocal?>?)

    @Query("Delete from Category")
    fun deleteCategory()

    @Transaction
    fun insertNewProducts(data: List<ProductPromoLocal?>?){
        deleteProducts()
        insertProducts(data)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertProducts(data: List<ProductPromoLocal?>?)

    @Query("Delete from ProductPromo")
    fun deleteProducts()

    @Query("SELECT * from ProductPromo where id=:idProduct")
    fun getProductById(idProduct:String): Single<ProductPromoLocal>

    @Query("UPDATE ProductPromo SET loved=:liked WHERE id=:idProduct")
    suspend fun updateLikedProduct(liked:Int,idProduct:String)

    @Query("SELECT * from ProductPromo")
    fun getProductList():Single<List<ProductPromoLocal>>
}