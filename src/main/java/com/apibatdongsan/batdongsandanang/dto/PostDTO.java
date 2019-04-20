package com.apibatdongsan.batdongsandanang.dto;

import lombok.Data;

@Data
public class PostDTO {

    Long id;

    String name;

    String description;

    Long acreage;

    Long roadInFrontOf;

    Long numberFloor;

    Long bedrooms;

    Long bathrooms;

    //relationship

    Long postTypeId;

    Long productTypeId;

    Long userId;
}
