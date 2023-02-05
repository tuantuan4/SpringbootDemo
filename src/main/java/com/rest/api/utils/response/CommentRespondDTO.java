package com.rest.api.utils.response;

import com.rest.api.entity.Post;
import lombok.Data;

@Data
public class CommentRespondDTO {
    private Long id;

    private String name;

    private String email;

    private String body;

    private Post post;

}
