package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.repositories.comment.CommentRepository;
import com.nancy.m6project.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Set<Comment> findCommentByStatusIdOrderByCreatedDateAsc(Long id) {
        return commentRepository.findCommentByStatusIdOrderByCreatedDateAsc(id);
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List findAll(Sort sort) {
        return null;
    }

    @Override
    public Page findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment save(Comment comment) throws SQLIntegrityConstraintViolationException {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Comment comment = findById(id);
        if( comment != null){
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
