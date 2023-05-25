package com.rest.api.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.api.entity.Post;
import lombok.Data;

@Data
public class CommentRespondDTO {
    private Long id;
    private String body;
    private Long postId;
    private Long userId;
    @JsonIgnore
    private Post post;

}
