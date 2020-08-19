package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.comment.CommentLike;
import com.nancy.m6project.repositories.comment.CommentRepository;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.like.CommentLikeService;
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

    @Autowired
    CommentLikeService commentLikeService;

    @Override
    public Set<Comment> findCommentByStatusIdOrderByCreatedDateAsc(Long id) {
        return commentRepository.findCommentByStatusIdOrderByCreatedDateAsc(id);
    }

    @Override
    public Iterable<Comment> findAllByStatus_Id(Long status_id) {
        return commentRepository.findAllByStatus_Id(status_id);
    }

    @Override
    public void deleteAllCommentByStatus_Id(Iterable<Comment> comments) {
        for (Comment anComment:comments) {
            this.delete(anComment.getId());
        }
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
        Iterable<CommentLike> commentLikes = commentLikeService.findAllByComment_Id(id) ;
        if(commentLikes != null){
            commentLikeService.deleteAllByCommentId(commentLikes);
        }
        if(comment != null){
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
