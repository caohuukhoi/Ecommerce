package com.khoich.ecommerceeeeeee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.khoich.ecommerceeeeeee.data.CartProduct
import com.khoich.ecommerceeeeeee.firebase.FirebaseCommon
import com.khoich.ecommerceeeeeee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel() {

    private val _addToCart = MutableStateFlow<Resource<CartProduct>>(Resource.Unspecified())
    val addToCart = _addToCart.asStateFlow()

    fun addUpdateProductInCart(cartProduct: CartProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            _addToCart.emit(Resource.Loading())
        }
        firestore.collection("user").document(auth.uid!!).collection("cart")
            .whereEqualTo("product.id", cartProduct.product.id).get()
            // cái này là khi add, nếu sản phẩm có trong giỏ hàng rồi thì tăng lên thôi, còn nếu ko thì add sản phẩm mới
            .addOnSuccessListener { query ->
                // nếu danh sách không rỗng, query.documents.let chính là kiểm tra có rỗng hay không
                query.documents.let {
                    // nếu danh sách truy xuất sản phẩm giống sản phẩm cần add rỗng thì add new
                    if (it.isEmpty()) { //Add new product
                        addNewProduct(cartProduct)
                    } else {
                        // it là các sản phẩm có id giống với sản phẩm muốn them, nếu danh sách không rỗng
                        // it.first() là để định dạng giống cái đầu tiên, vì chưa chắc đã có cái thứ 2
                        val cartProductNew = it.first().toObject(CartProduct::class.java)

                        // so sánh lần lượt các cái còn lại của đối tượng trong giỏ hàng, sản phẩm, màu, size
                        if (cartProductNew?.product == cartProduct.product
                            && cartProductNew.selectedColor == cartProduct.selectedColor
                            && cartProductNew.selectedSize == cartProduct.selectedSize
                        ) { //Increase the quantity (fixed quantity increasement issue)
                            val documentId = it.first().id
                            increaseQuantity(documentId, cartProduct)
                        } else { //Add new product
                            addNewProduct(cartProduct)
                        }
                    }
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _addToCart.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    private fun addNewProduct(cartProduct: CartProduct) {
        firebaseCommon.addProductToCart(cartProduct) { addedProduct, e ->
            viewModelScope.launch(Dispatchers.IO) {
                if (e == null)
                    _addToCart.emit(Resource.Success(addedProduct!!))
                else
                    _addToCart.emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun increaseQuantity(documentId: String, cartProduct: CartProduct) {
        firebaseCommon.increaseQuantity(documentId) { _, e ->
            viewModelScope.launch(Dispatchers.IO) {
                if (e == null)
                    _addToCart.emit(Resource.Success(cartProduct))
                else
                    _addToCart.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}