package com.khoich.ecommerceeeeeee.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.khoich.ecommerceeeeeee.adapter.BillingProductsAdapter
import com.khoich.ecommerceeeeeee.data.order.OrderStatus
import com.khoich.ecommerceeeeeee.data.order.getOrderStatus
import com.khoich.ecommerceeeeeee.databinding.FragmentOrderDetailBinding
import com.khoich.ecommerceeeeeee.util.VerticalItemDecoration
import com.khoich.ecommerceeeeeee.util.hideBottomNavigationView

class OrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailBinding
    private val billingProductsAdapter by lazy { BillingProductsAdapter() }
    private val args by navArgs<OrderDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideBottomNavigationView()
        binding = FragmentOrderDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val order = args.order

        setupOrderRv()

        binding.apply {

            val textOrderId = "Order #${order.orderId}"
            tvOrderId.text = textOrderId

            stepView.setSteps(
                mutableListOf(
                    OrderStatus.Ordered.status,
                    OrderStatus.Confirmed.status,
                    OrderStatus.Shipped.status,
                    OrderStatus.Delivered.status,
                )
            )

            val currentOrderState = when (getOrderStatus(order.orderStatus)) {
                is OrderStatus.Ordered -> 0
                is OrderStatus.Confirmed -> 1
                is OrderStatus.Shipped -> 2
                is OrderStatus.Delivered -> 3
                else -> 0
            }

            stepView.go(currentOrderState, true)
            if (currentOrderState == 3) {
                stepView.done(true)
            }

            val textAddress = "${order.address.street} ${order.address.city}"
            tvFullName.text = order.address.fullName
            tvAddress.text = textAddress
            tvPhoneNumber.text = order.address.phone

            val textTotalPrice = "$ ${order.totalPrice}"
            tvTotalPrice.text = textTotalPrice
        }

        billingProductsAdapter.differ.submitList(order.products)
    }

    private fun setupOrderRv() {
        binding.rvProducts.apply {
            adapter = billingProductsAdapter
            addItemDecoration(VerticalItemDecoration())
        }
    }
}