package com.example.vegait.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher
import com.bumptech.glide.Glide
import com.example.vegait.R
import com.example.vegait.entity.ProductEntity
import com.example.vegait.viewmodel.ProductDetailViewModel
import com.example.vegait.api.RequestState
import com.example.vegait.databinding.FragmentProductDetailsBinding
import com.example.vegait.fragment.BaseFragment
import com.example.vegait.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductDetailsFragment : BaseFragment() {

    private val viewModel: ProductDetailViewModel by viewModel()
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun moneyMask(): ValorMonetarioWatcher {
        return ValorMonetarioWatcher.Builder()
            .comMantemZerosAoLimpar()
            .comSimboloReal()
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (requireActivity() as  MainActivity).findViewById<FloatingActionButton>(R.id.fab)!!.visibility = GONE
        addObserve()
        val productId = arguments?.getInt("product_id")

        productId?.let {
            if (it > 0) {
                viewModel.getProduct(productId)
                binding.btDelete.setOnClickListener { viewModel.deleteProduct(productId) }
            }
        }

        val addProduct = arguments?.getBoolean("add_product")

        addProduct?.let {
            if (it) {
                binding.btSave.setOnClickListener {
                    viewModel.addProduct(binding.etTitle.text.toString())
                }

                binding.btDelete.visibility = View.INVISIBLE
            }
        }
    }

    private fun loadData(product: ProductEntity) {
        with(binding) {
            Glide.with(requireParentFragment())
                .load(product.thumbnail)
                .circleCrop()
                .into(binding.ivProductBig)
            etTitle.setText(product.title)
            etDescription.setText(product.description)
            etPrice.setText(product.price.toString())
            etDiscount.setText(product.discountPercentage.toString())
            etRating.setText(product.rating.toString())
            etStock.setText(product.stock.toString())
            etBrand.setText(product.brand)
            etCategory.setText(product.category)

            binding.btSave.setOnClickListener { viewModel.updateProduct(product = product) }
        }
    }

    private fun addObserve() {
        viewModel.product.observe(requireActivity()) {
            when (it) {
                is RequestState.Success -> {
                    binding.pbLoading.hide()
                    loadData(it.data)
                }
                is RequestState.Loading -> binding.pbLoading.show()
                is RequestState.Error -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_error_by_get_product_detail)
                }
            }
        }

        viewModel.deleteProduct.observe(requireActivity()) {
            when (it) {
                is RequestState.Success -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_delete_simulation_succesfully)
                }
                is RequestState.Loading -> binding.pbLoading.show()
                is RequestState.Error -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_error_delete_simulation)
                }
            }
        }

        viewModel.updateProduct.observe(requireActivity()) {
            when (it) {
                is RequestState.Success -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_update_simulation_successfully)
                }
                is RequestState.Loading -> binding.pbLoading.show()
                is RequestState.Error -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_error_update_simulation)
                }
            }
        }

        viewModel.addProduct.observe(requireActivity()) {
            when (it) {
                is RequestState.Success -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_insert_simulation_successfully)
                }
                is RequestState.Loading -> binding.pbLoading.show()
                is RequestState.Error -> {
                    binding.pbLoading.hide()
                    showToast(requireContext(), R.string.text_error_insert_simulation)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}