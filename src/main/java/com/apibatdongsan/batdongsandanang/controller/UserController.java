package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.dto.UserDTO;
import com.apibatdongsan.batdongsandanang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/api/user")
    public ApiResponseDTO createNewUserForCompany(@RequestBody UserDTO userDTO) {
        return new ApiResponseDTO(200, "Created", userService.createNewUser(userDTO, (long) 2));
    }

    @PostMapping(value = "/api/register")
    public ApiResponseDTO registerNewUser(@RequestBody UserDTO userDTO) {
        return new ApiResponseDTO(200, "Created", userService.createNewUser(userDTO, (long) 3));
    }

    
}
