package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select post.* from post order by id ASC ", nativeQuery = true)
    List<Post> findAllOrderByIdAsc();

    @Query(value = "SELECT post.* from post WHERE post.status = 1 ORDER by create_date DESC limit (10);", nativeQuery = true)
    List<Post> getTopNews();

    @Query(value = "  select post.* from post where post.id IN (select care_post_id from (select care_post.post_id as care_post_id  , count(*) as number_care  from care_post group by care_post.post_id order by number_care DESC limit (10))  as sort)", nativeQuery = true)
    List<Post> getTopCare();

    @Query(value = " select post.* from post where post.id IN (select favoritest_post_id from (select favourite.post_id as favoritest_post_id  , count(*) as number_favourite  from favourite group by favourite.post_id order by number_favourite DESC limit (10))  as sort)", nativeQuery = true)
    List<Post> getTopFavourite();

    @Query(value = "SELECT post.* from post WHERE post.status = 1 ORDER by id ASC ", nativeQuery = true)
    List<Post> getAllPostEnable();

    List<Post> findAllByUserAccountIdOrderByIdAsc(Long modId);
    Optional<Post> findFirstByNameIgnoreCase(String name);



//    @Query(value = "UPDATE public.post SET   favorite_persons = favorite_persons +1 WHERE id = ?1", nativeQuery = true)
//    int updatefavorites(Long idPost);

//    @Modifying
//    @Query(value = "update Post p set p.favoritePersons = (p.favoritePersons + 1 ) where p.id = ?",
//            nativeQuery = true)
//    int updatefavorites(Long id);


}
