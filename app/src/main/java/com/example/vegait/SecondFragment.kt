package com.example.vegait

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import com.example.vegait.databinding.FragmentSecondBinding
import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher
import com.bumptech.glide.Glide
import com.example.vegait.api.ProductDetailViewModel
import com.example.vegait.api.RequestState
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val viewModel: ProductDetailViewModel by viewModel()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
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
        viewModel.getProduct(productId!!)
        binding.btDelete.setOnClickListener { viewModel.deleteProduct(productId) }
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
                    Toast.makeText(requireContext(), "", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.deleteProduct.observe(requireActivity()) {
            when (it) {
                is RequestState.Success -> {
                    binding.pbLoading.hide()
                    Toast.makeText(requireContext(), "delete simulation succesfully !!!", Toast.LENGTH_LONG).show()
                }
                is RequestState.Loading -> binding.pbLoading.show()
                is RequestState.Error -> {
                    binding.pbLoading.hide()
                    Toast.makeText(requireContext(), "erro na simulacao de delete", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}