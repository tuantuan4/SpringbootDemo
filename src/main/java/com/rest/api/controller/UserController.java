package com.rest.api.controller;

import com.rest.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
//    @DeleteMapping("/delete/{idToDelete}")
//    public ResponseEntity<String> delete(@PathVariable("idToDelete") Long id){
//        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
//    }
    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{idToDelete}")
    public ResponseEntity<String> delete(@PathVariable("idToDelete") Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

}
