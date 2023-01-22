package com.rest.api.dto;

import com.rest.api.entity.Post;
import lombok.Data;

@Data

public class CommentDTO {

    private String name;

    private String email;

    private String body;

    private Long postId;
}
