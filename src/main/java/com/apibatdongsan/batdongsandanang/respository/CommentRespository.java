package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Comment;
import com.apibatdongsan.batdongsandanang.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRespository extends JpaRepository<Comment, Long> {
    List<Comment> findByOfPost(Post post);
    List<Comment> findByOfPostOrderByCommentDateDesc(Post post);

}
