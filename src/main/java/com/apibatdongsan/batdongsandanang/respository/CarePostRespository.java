package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.CarePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarePostRespository extends JpaRepository<CarePost, Long > {

    @Query(value = "select care_post.id  from care_post where care_post.post_id = ?1 and care_post.user_id = ?2", nativeQuery = true)
    Long getIdByPostIdandUserId(Long post_id, Long user_id);
}
