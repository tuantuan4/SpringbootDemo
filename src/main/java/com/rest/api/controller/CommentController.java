package com.rest.api.controller;

import com.rest.api.utils.request.dto.CommentDTO;
import com.rest.api.entity.Comment;
import com.rest.api.service.CommentService;
import com.rest.api.utils.response.CommentRespondDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * annotation in order to build RESTful API
 */
@RestController
/**
 * lombok, access modifiers is public, if access modifiers is private,
 * declare attribute @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
 */
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<CommentRespondDTO>> getAllComment(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentRespondDTO> createComment(@RequestBody CommentDTO dto) {
        return new ResponseEntity<>(commentService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CommentRespondDTO>> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<CommentRespondDTO> update(@RequestParam("id") Long id, @RequestBody CommentDTO dto){
        return new ResponseEntity<>(commentService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("idToDelete") Long id){
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }
}
