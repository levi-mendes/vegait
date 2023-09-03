package com.example.vegait.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vegait.usecase.DeleteProductUseCase
import com.example.vegait.usecase.ProductDetailUseCase
import com.example.vegait.entity.ProductEntity
import com.example.vegait.usecase.UpdateProductUseCase
import com.example.vegait.api.RequestState
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val useCase: ProductDetailUseCase,
    private val useCaseDelete: DeleteProductUseCase,
    private val useCaseUpdate: UpdateProductUseCase
): ViewModel() {

    private var _product = MutableLiveData<RequestState<ProductEntity>>()
    val product: LiveData<RequestState<ProductEntity>>
        get() = _product

    private var _deleteProduct = MutableLiveData<RequestState<ProductEntity>>()
    val deleteProduct: LiveData<RequestState<ProductEntity>>
        get() = _deleteProduct

    private var _updateProduct = MutableLiveData<RequestState<ProductEntity>>()
    val updateProduct: LiveData<RequestState<ProductEntity>>
        get() = _updateProduct

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

    fun updateProduct(product: ProductEntity) {
        _updateProduct.value = RequestState.Loading

        viewModelScope.launch {
            val response = useCaseUpdate.update(product = product)
            _updateProduct.value = RequestState.Success(response)
        }
    }
}