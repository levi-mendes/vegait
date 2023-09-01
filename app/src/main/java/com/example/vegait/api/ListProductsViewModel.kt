package com.example.vegait.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vegait.ListProductUseCase
import com.example.vegait.Product
import kotlinx.coroutines.launch

class ListProductsViewModel(
    private val useCase: ListProductUseCase
): ViewModel() {

    private var _products = MutableLiveData<RequestState<List<Product>>>()
    val products: LiveData<RequestState<List<Product>>>
        get() = _products

    fun listProducts() {
        _products.value = RequestState.Loading

        viewModelScope.launch {
            val products = useCase.products()
            _products.value = RequestState.Success(products)
        }

    }
}