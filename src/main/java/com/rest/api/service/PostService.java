package com.rest.api.service;

import com.rest.api.utils.request.dto.PostDTO;
import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.utils.response.PostRespondDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostRespondDTO> getAll();

    Optional<PostRespondDTO> findById(Long id);

    PostRespondDTO save(PostDTO dto);

    PostRespondDTO update(PostDTO dto, Long id);

    String delete(Long id);
}
