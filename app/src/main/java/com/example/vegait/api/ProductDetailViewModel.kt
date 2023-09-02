package com.example.vegait.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vegait.ProductDetailUseCase
import com.example.vegait.ProductEntity
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val useCase: ProductDetailUseCase
): ViewModel() {

    private var _product = MutableLiveData<RequestState<ProductEntity>>()
    val product: LiveData<RequestState<ProductEntity>>
        get() = _product

    fun getProduct(id: Int) {
        _product.value = RequestState.Loading

        viewModelScope.launch {
            val product = useCase.product(id)
            _product.value = RequestState.Success(product)
        }
    }
}