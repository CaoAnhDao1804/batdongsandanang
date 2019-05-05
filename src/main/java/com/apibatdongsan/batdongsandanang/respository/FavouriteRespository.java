package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRespository extends JpaRepository<Favourite, Long> {

    @Query(value = "select favourite.id  from favourite where favourite.post_id = ?1 and favourite.user_id = ?2", nativeQuery = true)
    Long getIdByPostIdandUserId(Long post_id, Long user_id);

    List<Favourite> findByUserId(Long userId);
}
