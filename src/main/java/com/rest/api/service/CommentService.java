package com.rest.api.service;

import com.rest.api.dto.CommentDTO;
import com.rest.api.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();

    Optional<Comment> findById(Long id);

    Comment save(CommentDTO dto);
}
