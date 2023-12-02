package com.khoich.ecommerceeeeeee.fragment.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khoich.ecommerceeeeeee.R
import com.khoich.ecommerceeeeeee.adapter.ColorsAdapter
import com.khoich.ecommerceeeeeee.adapter.SizesAdapter
import com.khoich.ecommerceeeeeee.adapter.ViewPager2Images
import com.khoich.ecommerceeeeeee.databinding.FragmentProductDetailsBinding
import com.khoich.ecommerceeeeeee.util.Resource
import com.khoich.ecommerceeeeeee.util.hideBottomNavigationView
import com.khoich.ecommerceeeeeee.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    // cái này có được sau khi thêm args trong navigation
    private val args by navArgs<ProductDetailsFragmentArgs>()

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images() }
    private val sizesAdapter by lazy { SizesAdapter() }
    private val colorsAdapter by lazy { ColorsAdapter() }
    private var selectedColor: Int? = null
    private var selectedSize: String? = null
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideBottomNavigationView()
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        setupSizesRv()
        setupColorsRv()
        setupViewpager()

        binding.imageClose.setOnClickListener {
            findNavController().popBackStack()
        }

        sizesAdapter.onItemClick = {
            selectedSize = it
        }

        colorsAdapter.onItemClick = {
            selectedColor = it
        }

        binding.buttonAddToCart.setOnClickListener {
//            viewModel.addUpdateProductInCart(CartProduct(product, 1, selectedColor, selectedSize))
        }

//        lifecycleScope.launch{
//            viewModel.addToCart.collectLatest {
//                when (it) {
//                    is Resource.Loading -> {
//                        binding.buttonAddToCart.startAnimation()
//                    }
//
//                    is Resource.Success -> {
//                        binding.buttonAddToCart.revertAnimation()
//                        binding.buttonAddToCart.setBackgroundColor(resources.getColor(R.color.black))
//                    }
//
//                    is Resource.Error -> {
//                        binding.buttonAddToCart.stopAnimation()
//                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//                    }
//                    else -> Unit
//                }
//            }
//        }

        binding.apply {
            val textProductPrice = "$ ${product.price}"
            tvProductName.text = product.name
            tvProductPrice.text = textProductPrice
            tvProductDescription.text = product.description

            if (product.colors.isNullOrEmpty())
                tvProductColors.visibility = View.INVISIBLE
            if (product.sizes.isNullOrEmpty())
                tvProductSize.visibility = View.INVISIBLE
        }

        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let {
            colorsAdapter.differ.submitList(it)
        }
        product.sizes?.let {
            sizesAdapter.differ.submitList(it)
        }
    }

    private fun setupViewpager() {
        binding.viewPagerProductImages.adapter = viewPagerAdapter
    }

    private fun setupColorsRv() {
        binding.rvColors.adapter = colorsAdapter
    }

    private fun setupSizesRv() {
        binding.rvSizes.adapter = sizesAdapter
    }
}