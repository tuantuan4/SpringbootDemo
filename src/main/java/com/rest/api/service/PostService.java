package com.rest.api.service;

import com.rest.api.utils.request.dto.PostDTO;
import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAll();

    Optional<Post> findById(Long id);

    Comment save(PostDTO dto);

    Comment update(PostDTO dto, Long id);

    String delete(Long id);
}
