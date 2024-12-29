package com.fromu.paging3androomtoyproject.domain.model

import com.fromu.paging3androomtoyproject.data.model.Photos

data class Photo(
    var id: Int? = null,
    var src: String? = null,
    var photographer: String? = null
)


fun Photos.toDomain(): Photo {
    return Photo(
        id = this.id,
        photographer = this.photographer ?: "Unknown Photographer",
        src = this.src.original
    )
}