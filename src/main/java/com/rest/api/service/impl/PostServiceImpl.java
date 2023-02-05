package com.rest.api.service.impl;

import com.rest.api.utils.request.dto.PostDTO;
import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.response.PostRespondDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest.api.service.impl.CommentServiceImpl.mapperToCommentDTO;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostRespondDTO> getAll() {
        return postRepository.findAll().stream().map(p -> mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostRespondDTO> findById(Long id) {
        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostRespondDTO save(PostDTO dto) {
        Post p = new Post();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());

        Post saved = postRepository.save(p);
        return mapperToPostDTO(saved);
    }

    @Override
    public PostRespondDTO update(PostDTO dto, Long id) {
        Post p = postRepository.findById(id).get();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        return mapperToPostDTO(postRepository.save(p));
    }

    @Override
    public String delete(Long id) {
        postRepository.deleteById(id);
        return "Delete Post Success";
    }

    private PostRespondDTO mapperToPostDTO(Post entity) {
        PostRespondDTO dto = new PostRespondDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
//        dto.setComments(entity.getComments().stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toSet()));
        return dto;
    }
}
