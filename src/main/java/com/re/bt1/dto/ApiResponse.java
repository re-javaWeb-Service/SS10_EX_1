package com.re.bt1.dto;

import lombok.*;


//ApiResponse<T> là lớp bao bọc (wrapper) toàn bộ dữ liệu trả về từ API nhằm chuẩn hóa định dạng response của hệ thống.
//Thay vì mỗi API trả về một cấu trúc khác nhau, tất cả kết quả thành công hoặc thất bại đều được đóng gói trong ApiResponse để client
// luôn nhận được cùng một format và dễ xử lý hơn.
//Ex
//{
//    "success": true,
//        "code": "SUCCESS",
//        "message": "Add product successfully",
//        "data": {
//    "id": 1,
//            "productId": "P01",
//            "quantity": 5
//}
//}

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int code;

}
