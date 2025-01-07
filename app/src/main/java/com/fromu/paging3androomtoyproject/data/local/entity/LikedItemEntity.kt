package com.fromu.paging3androomtoyproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_items")
data class LikedItemEntity(
    @PrimaryKey val id: Int, // 아이템 고유 ID
    val photographer: String,         // 제목
    val src: String       // 이미지 URL
)