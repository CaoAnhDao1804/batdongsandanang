package com.apibatdongsan.batdongsandanang.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String address;
    private String phone;
}
