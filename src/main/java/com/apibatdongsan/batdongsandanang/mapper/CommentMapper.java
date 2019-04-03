package com.apibatdongsan.batdongsandanang.mapper;

import com.apibatdongsan.batdongsandanang.dto.CommentDTO;
import com.apibatdongsan.batdongsandanang.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO commentTocommentDTO(Comment comment);

    Comment commentDtoToComment(CommentDTO commentDTO);
}
