package com.rest.api.service.impl;

import com.rest.api.utils.request.dto.CommentDTO;
import com.rest.api.entity.Comment;
import com.rest.api.repository.CommentRepository;
import com.rest.api.service.CommentService;
import com.rest.api.utils.response.CommentRespondDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentRespondDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(c -> mapperToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentRespondDTO> findById(Long id) {
        Comment comment = commentRepository.findById(id).get();
        return Optional.of(mapperToDTO(comment));
    }

    @Override
    public CommentRespondDTO save(CommentDTO comment) {
        Comment cmt = new Comment();
        cmt.setName(comment.getName());
        cmt.setEmail(comment.getEmail());
        cmt.setBody(comment.getBody());
//        cmt.setPost(dto.getPost());
        Comment saveComment = commentRepository.save(cmt);
        return mapperToDTO(saveComment);
    }

    @Override
    public CommentRespondDTO update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        return mapperToDTO(commentRepository.save(comment)) ;
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Success";
    }

    private CommentRespondDTO mapperToDTO(Comment comment) {
        CommentRespondDTO dto = new CommentRespondDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        dto.setName(comment.getName());
        dto.setPost(comment.getPost());
        return dto;
    }

}
