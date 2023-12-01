package com.khoich.ecommerceeeeeee.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khoich.ecommerceeeeeee.R
import com.khoich.ecommerceeeeeee.adapter.BestProductsAdapter
import com.khoich.ecommerceeeeeee.databinding.FragmentBaseCategoryBinding

open class BaseCategoryFragment: Fragment(R.layout.fragment_base_category) {
    private lateinit var binding: FragmentBaseCategoryBinding
    protected val offerAdapter: BestProductsAdapter by lazy { BestProductsAdapter() }
    protected val  bestProductsAdapter: BestProductsAdapter by lazy { BestProductsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBestProducts.adapter = bestProductsAdapter
        binding.rvOfferProducts.adapter = offerAdapter

//        bestProductsAdapter.onClick = {
//            val b = Bundle().apply { putParcelable("product",it) }
//            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
//        }
//
//        offerAdapter.onClick = {
//            val b = Bundle().apply { putParcelable("product",it) }
//            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
//        }
//
//        binding.rvOfferProducts.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                if (!recyclerView.canScrollVertically(1) && dx != 0){
//                    onOfferPagingRequest()
//                }
//            }
//        })

//        binding.nestedScrollBaseCategory.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{ v, _, scrollY, _, _ ->
//            if (v.getChildAt(0).bottom <= v.height + scrollY){
//                onBestProductsPagingRequest()
//            }
//        })
    }

//    fun showOfferLoading(){
//        binding.offerProductsProgressBar.visibility = View.VISIBLE
//    }
//
//    fun hideOfferLoading(){
//        binding.offerProductsProgressBar.visibility = View.GONE
//    }
//
//    fun showBestProductsLoading(){
//        binding.bestProductsProgressBar.visibility = View.VISIBLE
//    }
//
//    fun hideBestProductsLoading(){
//        binding.bestProductsProgressBar.visibility = View.GONE
//    }
//
//    open fun onOfferPagingRequest(){
//
//    }
//
//    open fun onBestProductsPagingRequest(){
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        showBottomNavigationView()
//    }

}