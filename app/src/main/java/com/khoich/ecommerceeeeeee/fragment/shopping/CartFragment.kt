package com.khoich.ecommerceeeeeee.fragment.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khoich.ecommerceeeeeee.R
import com.khoich.ecommerceeeeeee.adapter.CartProductAdapter
import com.khoich.ecommerceeeeeee.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

class CartFragment : Fragment(R.layout.fragment_cart){
    private lateinit var binding: FragmentCartBinding

    private val cartAdapter by lazy { CartProductAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCart.adapter = cartAdapter
    }
}