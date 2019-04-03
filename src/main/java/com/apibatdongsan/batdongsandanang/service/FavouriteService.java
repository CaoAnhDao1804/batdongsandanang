package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.respository.FavouriteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService {

    @Autowired
    FavouriteRespository favouriteRespository;

    public Favourite like ( Favourite favourite) {
        return favouriteRespository.save(favourite);
    }

    public void unlike (Long idLike) {
        favouriteRespository.deleteById(idLike);
    }
}
