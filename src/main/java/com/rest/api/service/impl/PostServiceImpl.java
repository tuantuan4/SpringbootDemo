package com.rest.api.service.impl;

import com.rest.api.entity.User;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repository.UserRepository;
import com.rest.api.utils.request.dto.PostDTO;
import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.response.PostRespondDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest.api.service.impl.CommentServiceImpl.mapperToCommentDTO;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public List<PostRespondDTO> getAll() {
        return postRepository.findAll().stream().map(p -> mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostRespondDTO> findById(Long id) {
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: " + id));
        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }


    @Override
    public PostRespondDTO save(PostDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User nou found with id"));
        Post p = new Post();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        p.setUser(user);
        Post saved = postRepository.save(p);
        return mapperToPostDTO(saved);
    }

    @Override
    public PostRespondDTO update(PostDTO dto, Long id) {
        Post p = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id: " + id));
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        return mapperToPostDTO(postRepository.save(p));
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found to delete id: " + id));
        postRepository.deleteById(id);
        return "Delete Post Success";
    }

    private PostRespondDTO mapperToPostDTO(Post entity) {
        PostRespondDTO dto = new PostRespondDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setUserId(entity.getUser().getId());
//        if(entity.getComments().size() > 0){
//            dto.setComments(entity.getComments().stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toSet()));
//        }
        return dto;
    }
}
