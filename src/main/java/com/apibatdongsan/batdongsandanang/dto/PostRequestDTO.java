package com.apibatdongsan.batdongsandanang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    Long id;

    String name;

    String description;

    String address;

    Long acreage;

    Long roadInFrontOf;

    Long numberFloor;

    Long bedrooms;

    Long bathrooms;

    Long status;

    Long postTypeId;

    Long productTypeId;

    String[] suroundings;

    String[] utilities;

    Long userId;

    String nameOwner;
    String mailOwner;
    String phoneOwner;

}
