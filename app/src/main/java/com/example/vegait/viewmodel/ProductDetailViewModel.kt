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
import com.example.vegait.entity.ProductCreatedEntity
import com.example.vegait.usecase.AddProductUseCase
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val useCase: ProductDetailUseCase,
    private val useCaseDelete: DeleteProductUseCase,
    private val useCaseUpdate: UpdateProductUseCase,
    private val useCaseAdd: AddProductUseCase
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

    private var _addProduct = MutableLiveData<RequestState<ProductCreatedEntity>>()
    val addProduct: LiveData<RequestState<ProductCreatedEntity>>
        get() = _addProduct

    fun getProduct(id: Int) {
        _product.value = RequestState.Loading

        viewModelScope.launch {
            runCatching {
                useCase.product(id)
            }.onSuccess {
                _product.value = RequestState.Success(it)
            }.onFailure {
                _product.value = RequestState.Error(it)
            }
        }
    }

    fun deleteProduct(id: Int) {
        _deleteProduct.value = RequestState.Loading

        viewModelScope.launch {
            runCatching {
                useCaseDelete.deleteProduct(id = id)
            }.onSuccess {
                _deleteProduct.value = RequestState.Success(it)
            }.onFailure {
                _deleteProduct.value = RequestState.Error(it)
            }
        }
    }

    fun updateProduct(product: ProductEntity) {
        _updateProduct.value = RequestState.Loading

        viewModelScope.launch {
            runCatching {
                useCaseUpdate.update(product = product)
            }.onSuccess {
                _updateProduct.value = RequestState.Success(it)
            }.onFailure {
                _updateProduct.value = RequestState.Error(it)
            }
        }
    }

    fun addProduct(title: String) {
        _addProduct.value = RequestState.Loading

        viewModelScope.launch {
            runCatching {
                useCaseAdd.addProduct(title = title)
            }.onSuccess {
                _addProduct.value = RequestState.Success(it)
            }.onFailure {
                _addProduct.value = RequestState.Error(it)
            }
        }
    }
}