package com.raka.qtest.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class CategoryLocal(
	@ColumnInfo(name = "roomId")
	@PrimaryKey(autoGenerate = true)
	val roomId: Long = 0,
	@ColumnInfo(name = "imageUrl")
	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,
	@ColumnInfo(name = "name")
	@field:SerializedName("name")
	val name: String? = null,
	@ColumnInfo(name = "id")
	@field:SerializedName("id")
	val id: Int? = null
)