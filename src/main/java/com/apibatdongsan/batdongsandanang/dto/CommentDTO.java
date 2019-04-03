package com.apibatdongsan.batdongsandanang.dto;

import lombok.Data;

@Data
public class CommentDTO {
    Long postId;

    Long userId;

    String content;

}
