package com.khoich.ecommerceeeeeee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoich.ecommerceeeeeee.data.Product
import com.khoich.ecommerceeeeeee.databinding.SpecialRvItemBinding

class SpecialProductsAdapter : RecyclerView.Adapter<SpecialProductsAdapter.SpecialProductsViewHolder>() {

    // binding.root là gốc là cái filer XML của item, còn binding chỉ là 1 biến có kiểu dữ liệu là SpecialRvITemBinding thôi
    inner class SpecialProductsViewHolder(private val binding: SpecialRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imageSpecialRvItem)
                tvSpecialProductName.text = product.name
                tvSpecialPrdouctPrice.text = product.price.toString()
            }
        }
    }

    // lần cập nhật đầu tiên lúc không có danh sách cũ thì sẽ coi tất cả các mục là cần cập nhật
    // theo dõi sự thay đổi của danh sách truyền vào
    // kiểu so sánh lại lần lượt từng item ở mỗi vị trí của 2 list cũ và list mới, tương đương với oldItem và newItem
    // kiểu 0-0,1-1,2-2,..., nếu danh sách cũ thay đổi số lượng kiểu bớt đi,
    // thì item cũ không tồn tại thì sẽ coi như cập nhật, tương tụ với thêm
    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductsViewHolder {
        return SpecialProductsViewHolder(
            SpecialRvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SpecialProductsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((Product) -> Unit)? = null

}