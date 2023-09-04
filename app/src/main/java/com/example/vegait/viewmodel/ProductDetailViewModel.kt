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
            try {
                val product = useCase.product(id)
                _product.value = RequestState.Success(product)
            } catch (e: Exception) {
                _product.value = RequestState.Error(e)
            }
        }
    }

    fun deleteProduct(id: Int) {
        _deleteProduct.value = RequestState.Loading

        viewModelScope.launch {
            try {
                val product = useCaseDelete.deleteProduct(id = id)
                _deleteProduct.value = RequestState.Success(product)
            } catch (e: Exception) {
                _deleteProduct.value = RequestState.Error(e)
            }
        }
    }

    fun updateProduct(product: ProductEntity) {
        _updateProduct.value = RequestState.Loading

        viewModelScope.launch {
            try {
                val response = useCaseUpdate.update(product = product)
                _updateProduct.value = RequestState.Success(response)
            } catch (e: Exception) {
                _updateProduct.value = RequestState.Error(e)
            }
        }
    }

    fun addProduct(title: String) {
        _addProduct.value = RequestState.Loading

        viewModelScope.launch {
            try {
                val response = useCaseAdd.addProduct(title = title)
                _addProduct.value = RequestState.Success(response)
            } catch (e: Exception) {
                _addProduct.value = RequestState.Error(e)
            }
        }
    }
}