package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select post.* from post order by id ASC ", nativeQuery = true)
    List<Post> findAllOrderByIdAsc();

    @Query(value = "SELECT post.* from post ORDER by create_date DESC limit (10);", nativeQuery = true)
    List<Post> getTopNews();

    List<Post> findAllByUserIdOrderByIdAsc(Long modId);



//    @Query(value = "UPDATE public.post SET   favorite_persons = favorite_persons +1 WHERE id = ?1", nativeQuery = true)
//    int updatefavorites(Long idPost);

//    @Modifying
//    @Query(value = "update Post p set p.favoritePersons = (p.favoritePersons + 1 ) where p.id = ?",
//            nativeQuery = true)
//    int updatefavorites(Long id);


}
