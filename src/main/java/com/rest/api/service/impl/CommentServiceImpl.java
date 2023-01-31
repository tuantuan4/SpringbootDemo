package com.rest.api.service.impl;

import com.rest.api.dto.CommentDTO;
import com.rest.api.entity.Comment;
import com.rest.api.repository.CommentRepository;
import com.rest.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(CommentDTO dto) {
        Comment cmt = new Comment();
        cmt.setName(dto.getName());
        cmt.setEmail(dto.getEmail());
        cmt.setBody(dto.getBody());
//        cmt.setPost(dto.getPost());
        return commentRepository.save(cmt);
    }

    @Override
    public Comment update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        return commentRepository.save(comment);
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Success";
    }


}
