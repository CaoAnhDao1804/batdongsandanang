package com.apibatdongsan.batdongsandanang.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {
    Long id;
    Long idUser;
    String username;
    String address;
    String content;
    Long postId;
    Date createDate;

}
