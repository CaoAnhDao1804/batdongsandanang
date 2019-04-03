package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

    @PostMapping(value = "/api/like")
    public ApiResponseDTO like(@RequestBody Favourite favourite) {
            return new ApiResponseDTO(200, "Liked", favouriteService.like(favourite));
    }

    @DeleteMapping(value = "/api/like/{idLike}")
    public ApiResponseDTO unlike(@PathVariable("idLike") Long idLike) {
        favouriteService.unlike(idLike);
        return new ApiResponseDTO(200, "Unlike", false);
    }
}
