package com.raka.qtest.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProductPromo")
data class ProductPromoLocal(
    @ColumnInfo(name = "roomId")
    @PrimaryKey(autoGenerate = true)
    val roomId: Long = 0,
    @ColumnInfo(name = "loved")
    @field:SerializedName("loved")
    val loved: Int? = null,
    @ColumnInfo(name = "price")
    @field:SerializedName("price")
    val price: String? = null,
    @ColumnInfo(name = "imageUrl")
    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,
    @ColumnInfo(name = "description")
    @field:SerializedName("description")
    val description: String? = null,
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: String? = null,
    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String? = null
)