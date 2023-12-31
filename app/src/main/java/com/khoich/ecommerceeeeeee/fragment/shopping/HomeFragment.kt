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
import com.khoich.ecommerceeeeeee.util.showBottomNavigationView

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
            FurnitureFragment(),
            ChairFragment(),
            TableFragment(),
            AccessoryFragment(),
            CupboardFragment(),
        )

        binding.viewpagerHome.isUserInputEnabled = false

        val viewPager2Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter

        // cái này để truyền vào tabLayout các danh mục, nó sẽ dựa vào số lượng các fragment để tạo tablayout
        // cai TabLayoutMediator tự động liên kết các fragment trong viewpager với các tab
        // số lượng tab dựa vào số lượng fragment có trong binding.viewpagerHome
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Furniture"
                2 -> tab.text = "Chair"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Cupboard"
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}