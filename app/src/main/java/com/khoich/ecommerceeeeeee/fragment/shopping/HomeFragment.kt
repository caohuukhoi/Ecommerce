package com.khoich.ecommerceeeeeee.fragment.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.khoich.ecommerceeeeeee.R
import com.khoich.ecommerceeeeeee.adapter.HomeViewpagerAdapter
import com.khoich.ecommerceeeeeee.databinding.FragmentHomeBinding
import com.khoich.ecommerceeeeeee.fragment.categories.AccessoryFragment
import com.khoich.ecommerceeeeeee.fragment.categories.ChairFragment
import com.khoich.ecommerceeeeeee.fragment.categories.CupboardFragment
import com.khoich.ecommerceeeeeee.fragment.categories.FurnitureFragment
import com.khoich.ecommerceeeeeee.fragment.categories.MainCategoryFragment
import com.khoich.ecommerceeeeeee.fragment.categories.TableFragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // truyền các frament vào
        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment(),
        )
        val viewPager2Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter

        // cái này để truyền vào tabLayout các danh mục, nó sẽ dựa vào số lượng các fragment để tạo tablayout
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }
}