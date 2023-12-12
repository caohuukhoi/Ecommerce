package com.khoich.ecommerceeeeeee.fragment.shopping.user

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
import com.khoich.ecommerceeeeeee.data.Address
import com.khoich.ecommerceeeeeee.databinding.FragmentAddAddressBinding
import com.khoich.ecommerceeeeeee.util.Resource
import com.khoich.ecommerceeeeeee.util.hideBottomNavigationView
import com.khoich.ecommerceeeeeee.viewmodel.AddAddressViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddAddressFragment : Fragment() {
    private lateinit var binding: FragmentAddAddressBinding
    val viewModel by viewModels<AddAddressViewModel>()
//    private val args by navArgs<AddressFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imageAddressClose.setOnClickListener{
            findNavController().popBackStack()
        }
        lifecycleScope.launch {
            viewModel.addNewAddress.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressbarAddress.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.progressbarAddress.visibility = View.INVISIBLE
                        findNavController().popBackStack()
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        hideBottomNavigationView()
        binding = FragmentAddAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val address = args.address
//        binding.apply {
//            edAddressTitle.setText(address.addressTitle)
//            edFullName.setText(address.fullName)
//            edState.setText(address.street)
//            edPhone.setText(address.phone)
//            edCity.setText(address.city)
//            edState.setText(address.state)
//        }

        binding.apply {
            buttonSave.setOnClickListener {
                val addressTitle = edAddressTitle.text.toString()
                val fullName = edFullName.text.toString()
                val street = edStreet.text.toString()
                val phone = edPhone.text.toString()
                val city = edCity.text.toString()
                val state = edState.text.toString()
                val address = Address(addressTitle, fullName, street, phone, city, state)
                viewModel.addAddress(address)
            }
        }


    }

}