package com.rest.api.service.impl;

import com.rest.api.entity.Post;
import com.rest.api.entity.User;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repository.PostRepository;
import com.rest.api.repository.UserRepository;
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

    private final PostRepository postRepository;

    private final UserRepository userRepository;
    @Override
    public List<CommentRespondDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentRespondDTO> findById(Long id) {
        commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found"));
        Comment comment = commentRepository.findById(id).get();
        return Optional.of(mapperToCommentDTO(comment));
    }

    @Override
    public CommentRespondDTO save(CommentDTO comment, Long idUser) {
        Post p =postRepository.findById(comment.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id: "));
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Comment cmt = new Comment();
        cmt.setBody(comment.getBody());
        cmt.setPost(p);
        cmt.setUser(user);
        Comment saveComment = commentRepository.save(cmt);
        return mapperToCommentDTO(saveComment);
    }

    @Override
    public CommentRespondDTO update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found to update"));
        Post p = postRepository.findById(dto.getPostId())
                        .orElseThrow(()-> new ResourceNotFoundException("Post not found"));
        comment.setBody(dto.getBody());
        comment.setPost(p);
        return mapperToCommentDTO(commentRepository.save(comment)) ;
    }

    @Override
    public String delete(Long id) {
        Comment cmt = commentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.deleteById(id);
        return "Delete Comment Success";
    }

    @Override
    public List<CommentRespondDTO> getAllCommentsInPost(Long idPost) {
        postRepository.findById(idPost)
                .orElseThrow(()-> new ResourceNotFoundException("Post is not found"));
        List<Comment> comments = commentRepository.getCommentsPost(idPost);
        return comments.stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toList());
    }

    public CommentRespondDTO mapperToCommentDTO(Comment comment) {
        CommentRespondDTO dto = new CommentRespondDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setPost(comment.getPost());
        dto.setUserId(comment.getUser().getId());
        return dto;
    }

}
