package com.apibatdongsan.batdongsandanang.mapper;

import com.apibatdongsan.batdongsandanang.dto.PostDTO;
import com.apibatdongsan.batdongsandanang.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO postToPostDTO(Post post);

    Post postDtoToPost(PostDTO postDTO);
}
