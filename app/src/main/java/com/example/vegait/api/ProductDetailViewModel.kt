package com.example.vegait.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vegait.DeleteProductUseCase
import com.example.vegait.ProductDetailUseCase
import com.example.vegait.ProductEntity
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val useCase: ProductDetailUseCase,
    private val useCaseDelete: DeleteProductUseCase
): ViewModel() {

    private var _product = MutableLiveData<RequestState<ProductEntity>>()
    val product: LiveData<RequestState<ProductEntity>>
        get() = _product

    private var _deleteProduct = MutableLiveData<RequestState<ProductEntity>>()
    val deleteProduct: LiveData<RequestState<ProductEntity>>
        get() = _deleteProduct

    fun getProduct(id: Int) {
        _product.value = RequestState.Loading

        viewModelScope.launch {
            val product = useCase.product(id)
            _product.value = RequestState.Success(product)
        }
    }

    fun deleteProduct(id: Int) {
        _deleteProduct.value = RequestState.Loading

        viewModelScope.launch {
            val product = useCaseDelete.deleteProduct(id = id)
            _deleteProduct.value = RequestState.Success(product)
        }
    }
}