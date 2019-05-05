package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.respository.FavouriteRespository;
import com.apibatdongsan.batdongsandanang.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    FavouriteRespository favouriteRespository;

    @Autowired
    PostRepository postRepository;

    public Favourite like(Favourite favourite) {
        return favouriteRespository.save(favourite);
    }

    public void unlike(Long idLike) {
        favouriteRespository.deleteById(idLike);
    }

    public Long getIdByUserIdAndPostId(Long userId, Long postId) {
        return favouriteRespository.getIdByPostIdandUserId(postId, userId);
    }

    public Favourite getById(Long id) {
        return favouriteRespository.findById(id).get();
    }

    public Favourite save(Long userId, Long postId) {
        Favourite  favourite = new Favourite();
        favourite.setUserId(userId);
        favourite.setPostId(postId);
//        int rows = postRepository.updatefavorites(postId);
        return favouriteRespository.save(favourite);
    }

    public List<Post> getFavortitePostByUserId(Long userId) {
        List<Favourite> favouritesOfUser = favouriteRespository.findByUserId(userId);
        List<Post> posts = new ArrayList<>();

        for (Favourite favourite: favouritesOfUser) {
            Post post = postRepository.findById(favourite.getPostId()).get();
            posts.add(post);
        }

        return posts;
    }

    public int numberFavoritePersonByIdPost(Long postId) {
        List<Favourite> favourites = favouriteRespository.findByPostId(postId);
        if (favourites == null) {
            return 0;
        } else {
            return favourites.size();
        }
    }
}
