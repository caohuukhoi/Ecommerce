package com.khoich.ecommerceeeeeee.fragment.shopping.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khoich.ecommerceeeeeee.R
import com.khoich.ecommerceeeeeee.databinding.FragmentAddAddressBinding
import com.khoich.ecommerceeeeeee.databinding.FragmentAllAddressBinding
import com.khoich.ecommerceeeeeee.databinding.FragmentProfileBinding
import com.khoich.ecommerceeeeeee.util.hideBottomNavigationView

class AllAddressFragment : Fragment() {
    private lateinit var binding: FragmentAllAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideBottomNavigationView()
        binding = FragmentAllAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}