package com.rest.api.service;

import com.rest.api.utils.request.dto.CommentDTO;
import com.rest.api.entity.Comment;
import com.rest.api.utils.response.CommentRespondDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentRespondDTO> getAll();

    Optional<CommentRespondDTO> findById(Long id);

    CommentRespondDTO save(CommentDTO comment, Long idUser);

    CommentRespondDTO update(CommentDTO dto, Long id);

    String delete(Long id);

    List<CommentRespondDTO> getAllCommentsInPost(Long idPost);
}
