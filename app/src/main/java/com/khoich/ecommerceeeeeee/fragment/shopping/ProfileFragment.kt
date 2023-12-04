package com.khoich.ecommerceeeeeee.fragment.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khoich.ecommerceeeeeee.R
import com.khoich.ecommerceeeeeee.databinding.FragmentProfileBinding
import com.khoich.ecommerceeeeeee.util.hideBottomNavigationView
import com.khoich.ecommerceeeeeee.util.showBottomNavigationView

class ProfileFragment : Fragment(R.layout.fragment_profile){
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_allOrdersFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}