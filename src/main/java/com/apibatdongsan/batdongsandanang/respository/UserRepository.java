package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    @Query(value = "select user_account.* from user_account order by id ASC ", nativeQuery = true)
    List<UserAccount> findAllOrderByIdAsc();
    UserAccount getByUsernameAndStatus(String name, Long status);

}
