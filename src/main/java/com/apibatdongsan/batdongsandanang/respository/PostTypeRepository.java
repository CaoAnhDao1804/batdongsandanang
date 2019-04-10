package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.PostType;
import com.apibatdongsan.batdongsandanang.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostTypeRepository extends JpaRepository<PostType, Long> {

    @Query(value = "select post_type.* from post_type order by id ASC ", nativeQuery = true)
    List<PostType> findAllOrderByIdAsc();
}
