package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.repositories.comment.CommentRepository;
import com.nancy.m6project.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Set<Comment> findCommentByStatusIdOrderByCreateDateAsc(Long id) {
        return commentRepository.findCommentByStatusIdOrderByCreateDateAsc(id);
    }

}
