package com.example.vegait.mapper

import com.example.vegait.api.response.ProductDeletedDTO
import com.example.vegait.api.response.ProductDetailDTO
import com.example.vegait.api.response.ProductUpdateDTO
import com.example.vegait.api.request.UpdateProductRequest
import com.example.vegait.api.response.ProductCreatedDTO
import com.example.vegait.api.response.ProductDTO
import com.example.vegait.entity.ProductCreatedEntity
import com.example.vegait.entity.ProductEntity


fun ProductDTO.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail
    )
}

fun ProductDetailDTO.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail
    )
}

fun ProductEntity.toProductRequest(): UpdateProductRequest {
    return UpdateProductRequest(title = title)
}

fun ProductDeletedDTO.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail
    )
}

fun ProductUpdateDTO.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail
    )
}

fun ProductCreatedDTO.toEntity(): ProductCreatedEntity {
    return ProductCreatedEntity(
        id = id,
        title = title
    )
}