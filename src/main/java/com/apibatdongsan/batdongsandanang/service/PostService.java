package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.dto.PostDTO;
import com.apibatdongsan.batdongsandanang.dto.PostRequestDTO;
import com.apibatdongsan.batdongsandanang.entity.*;
import com.apibatdongsan.batdongsandanang.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostTypeRepository postTypeRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PicturesRepository picturesRepository;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    SuroundingRepository suroundingRepository;

    @Autowired
    UtilitieRepository utilitieRepository;

    @Autowired
    FavouriteRespository favouriteRespository;


    public Post create(PostDTO postDTO) {
        return postRepository.save(convertPostDTOToPost(postDTO));
    }

    public Post convertPostDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setName(postDTO.getName());
        post.setAcreage(postDTO.getAcreage());
        post.setBathrooms(postDTO.getBathrooms());
        post.setBedrooms(postDTO.getBedrooms());
        post.setDescription(postDTO.getDescription());
        post.setNumberFloor(postDTO.getNumberFloor());
        post.setRoadInFrontOf(postDTO.getRoadInFrontOf());

        post.setPostType(postTypeRepository.findById(postDTO.getPostTypeId()).get());
        post.setProductType(productTypeRepository.findById(postDTO.getProductTypeId()).get());
        post.setUserAccount(userRepository.findById(postDTO.getUserId()).get());

        return post;
    }


    public Post update(PostDTO postDTO, Long id) {
        Post post = convertPostDTOToPost(postDTO);
        post.setId(id);
        return postRepository.save(post);
    }

    public List<Post> getAll() {
        return postRepository.findAllOrderByIdAsc();
    }

    public Post changeStatus(Long id) {
        Post oldPost = postRepository.findById(id).get();
        if (oldPost.getStatus() == 0) {
            oldPost.setStatus(1L);
            return postRepository.save(oldPost);
        } else {
            oldPost.setStatus(0L);
            return postRepository.save(oldPost);
        }
    }

    public PostType getById(Long id) {
        return postTypeRepository.findById(id).get();
    }

    public void addImages(Post post, MultipartFile[] files, MultipartFile fileCover) {
        try {
            List<PictureEntity> picturesEntityList = new ArrayList<>();
            if (fileCover != null) {
                String fileName = fileStorageService.storeFile(fileCover);
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/images/")
                        .path(fileName)
                        .toUriString();
                PictureEntity obj = new PictureEntity();
                obj.setUrl(fileDownloadUri);
                obj.setPost(post);
                obj.setType(1);
                PictureEntity pictureEntity = picturesRepository.save(obj);
                picturesEntityList.add(pictureEntity);
            }

            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    String fileName = fileStorageService.storeFile(file);
                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/images/")
                            .path(fileName)
                            .toUriString();
                    PictureEntity obj = new PictureEntity();
                    obj.setUrl(fileDownloadUri);
                    obj.setPost(post);
                    obj.setType(0);
                    PictureEntity pictureEntity = picturesRepository.save(obj);
                    picturesEntityList.add(pictureEntity);
                }
            }

            post.setPictureEntities(picturesEntityList);
        } catch (Exception e) {
            System.out.println("eror image");
            System.out.println(e.getMessage());
        }
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post createNewPost(PostRequestDTO postDTO) {
        Post post = convertFromPostRequestDTOToPost(postDTO);

        return postRepository.save(post);
    }

    public Post convertFromPostRequestDTOToPost(PostRequestDTO postRequestDTO) {
        Post post = new Post();
        post.setId(postRequestDTO.getId());
        post.setName(postRequestDTO.getName());
        post.setDescription(postRequestDTO.getDescription());
        post.setAddress(postRequestDTO.getAddress());
        post.setAcreage(postRequestDTO.getAcreage());
        post.setRoadInFrontOf(postRequestDTO.getRoadInFrontOf());
        post.setNumberFloor(postRequestDTO.getNumberFloor());
        post.setBedrooms(postRequestDTO.getBedrooms());
        post.setBathrooms(postRequestDTO.getBathrooms());
        post.setStatus(1L);
        post.setCreateDate( new Date());

        UserAccount userAccount = userRepository.findFirstById(postRequestDTO.getUserId());
        post.setUserAccount(userAccount);

        PostType postType = postTypeRepository.findById(postRequestDTO.getPostTypeId()).get();
        post.setPostType(postType);

        ProductType productType = productTypeRepository.findById(postRequestDTO.getPostTypeId()).get();
        post.setProductType(productType);

        String[] sur_ids = postRequestDTO.getSuroundings();
        List<Surounding> surroundings = new ArrayList<>();
        for (int i = 0; i < sur_ids.length; i++) {

            if (suroundingRepository.findById(Long.valueOf(sur_ids[i])).isPresent()) {
                Surounding surrounding = suroundingRepository.findById(Long.valueOf(sur_ids[i])).get();
                surroundings.add(surrounding);
            }
        }
        post.setSuroundings(surroundings);

        String[] uti_ids = postRequestDTO.getUtilities();
        List<Utilities> utilities = new ArrayList<>();
        for (int i = 0; i < uti_ids.length; i++) {
            if (utilitieRepository.findById(Long.valueOf(uti_ids[i])).isPresent()){
                Utilities newUtilities = utilitieRepository.findById(Long.valueOf(uti_ids[i])).get();
                utilities.add(newUtilities);
            }

        }
        post.setUtilities(utilities);

        return post;

    }

    public Post findById(Long id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    public List<PictureEntity> findPicturesById(Long id) {
        List<PictureEntity>  pictureEntities = picturesRepository.findPicturesByIdPost(id);
        return pictureEntities;
    }

    public List<Post> getTopFavorite(int topNumber) {
        List<Post> posts = postRepository.getTopFavourite();
        return posts;
    }

    public List<Post> getTopCare(int topNumber) {
        List<Post> posts = postRepository.getTopCare();
        return posts;

    }

    public List<Post> getTopNew(int topNumber) {
        List<Post> posts = new ArrayList<>();
        posts = postRepository.getTopNews();
        return posts;
    }

    public List<Post> getPostofMod(Long modId) {
        return postRepository.findAllByUserAccountIdOrderByIdAsc(modId);
    }

    public boolean isExist(String name) {
//        String namenormal = org.apache.commons.lang3.StringUtils.normalizeSpace(name);
        return postRepository.findFirstByNameIgnoreCase(name).isPresent();
    }

    public boolean isExistExcepCurrent(PostRequestDTO postDTO) {
        Optional<Post> post = postRepository.findFirstByNameIgnoreCase(postDTO.getName());
        if (!post.isPresent()) {
            return false;
        }
        Post post1 = post.get();
        if (post1 == null) return false;
        if (post1.getId() != postDTO.getId()) {
            return true;
        }
        return false;

    }

    public List<Post> getAllPostEnable() {
        return postRepository.getAllPostEnable();
    }
}
