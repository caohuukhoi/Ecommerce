package com.khoich.ecommerceeeeeee.util

// cái này dùng để định nghĩa các lớp kết quả, bắt buộc phải ghi đủ khi dùng when
// nếu không thì phải dùng else -> Unit để mặc định các kết quả khác
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    // những lớp con dưới là lấy dữ liệu từ ViewModel, để super lớp cha dữ liệu đó
    // khi gọi when thì nó chỉ có it thôi, tránh việc phải sửa chữa nhiều khi phải sửa ở cả ViewModel và Fragment nếu muốn sửa
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String): Resource<T>(message = message)
    class Loading<T>: Resource<T>()
    class Unspecified<T> : Resource<T>()
}