package com.example.vegait

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vegait.databinding.FragmentSecondBinding
import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher
import com.bumptech.glide.Glide
import com.example.vegait.api.ProductDetailViewModel
import com.example.vegait.api.RequestState
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

        arguments?.let {
//            Fir
        }
        addObserve()
        viewModel.getProduct(id)
    }

    private fun loadData(product: ProductEntity) {
        Glide.with(this)
            .load(product.thumbnail)
            .circleCrop()
            .into(binding.ivProductBig)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}