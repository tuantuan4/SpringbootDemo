package com.rest.api.utils.request.dto;

import com.rest.api.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private String title;

    private String description;

    private String content;

    private Set<Comment> comments;

}
