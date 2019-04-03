package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRespository extends JpaRepository<Favourite, Long> {
}
