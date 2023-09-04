package com.example.vegait.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher
import com.bumptech.glide.Glide
import com.example.vegait.entity.ProductEntity
import com.example.vegait.R
import com.example.vegait.activity.MainActivity
import com.example.vegait.viewmodel.ListProductsViewModel
import com.example.vegait.api.RequestState
import com.example.vegait.databinding.FragmentProductListingBinding
import com.example.vegait.databinding.ItemProductListBinding
import com.example.vegait.details.ProductDetailsFragment.Companion.EXTRA_PRODUCT_ID
import com.example.vegait.fragment.BaseFragment
import com.example.vegait.util.isOnline
import com.example.vegait.util.showAlert
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductListingFragment : BaseFragment() {

    private var _binding: FragmentProductListingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListProductsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun configList() {
        binding.rvPoducts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configList()
        addObservable()

        callListProducts()

        binding.btTryAgain.setOnClickListener {
            callListProducts()
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).findViewById<FloatingActionButton>(R.id.fab)!!
            .visibility = View.VISIBLE
    }

    private fun callListProducts() {
        if (!isOnline(requireContext())) {
            showAlert(requireContext(), R.string.text_error_no_internet_connection)
            binding.btTryAgain.visibility = View.VISIBLE
            binding.pbLoading.hide()
            return
        }

        viewModel.listProducts()
    }

    private fun addObservable() {
        viewModel.products.observe(requireActivity()) {
            when (it) {
                is RequestState.Success -> {
                    binding.pbLoading.hide()
                    binding.rvPoducts.adapter = ProductListAdapter(this, it.data)
                } is RequestState.Loading -> {
                    binding.btTryAgain.visibility = View.GONE
                    binding.pbLoading.show()
                } is RequestState.Error -> {
                    binding.btTryAgain.visibility = View.VISIBLE
                    binding.pbLoading.hide()
                    showAlert(requireContext(), R.string.text_error_listing_products)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class ProductListAdapter(
        private val context: Fragment,
        private val products: List<ProductEntity>) :
        RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemBiding = ItemProductListBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)

            return MyViewHolder(itemBiding, context)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val musica = products[position]

            holder.bind(musica)
        }

        override fun getItemCount() = products.size

        class MyViewHolder(
            private val itemBiding: ItemProductListBinding,
            private val fragment: Fragment):
            RecyclerView.ViewHolder(itemBiding.root) {

            fun bind(product: ProductEntity) {
                with(itemBiding) {
                    Glide.with(fragment)
                        .load(product.thumbnail)
                        .circleCrop()
                        .into(ivProduct)
                    tvTitle.text = product.title
                    tvPrice.addTextChangedListener(moneyMask())
                    tvPrice.text = product.price.toString()
                    root.setOnClickListener {
                        val bundle = bundleOf(EXTRA_PRODUCT_ID to product.id)
                        fragment.findNavController().navigate(
                            R.id.action_FirstFragment_to_SecondFragment, bundle)
                    }
                }
            }

            private fun moneyMask(): ValorMonetarioWatcher {
                return ValorMonetarioWatcher.Builder()
                    .comMantemZerosAoLimpar()
                    .comSimboloReal()
                    .build()
            }
        }
    }
}