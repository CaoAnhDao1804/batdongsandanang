package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicturesRepository extends JpaRepository<PictureEntity, Integer> {
    @Query(value = "select pictures.*  from pictures where pictures.post_id = ?1", nativeQuery = true)
    List<PictureEntity> findPicturesByIdPost(Long id);
}
