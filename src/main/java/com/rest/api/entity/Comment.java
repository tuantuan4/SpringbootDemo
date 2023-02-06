package com.rest.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @GeneratedValue: sinh giá trị cho khoá chính tự động, có 4 loại GeneratedType là AUTO, TABLE, SEQUENCE, IDENTITY
 * GeneratedType.IDENTITY: sau khi 1 bản ghi tạo ra thì giá trị tăng dần
 *

 */
@Getter
@Setter
@Entity
@Table(name = "comment", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
