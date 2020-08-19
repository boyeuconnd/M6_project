package com.nancy.m6project.service.impl;


import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.repositories.friendRequest.FriendRequestRepositories;
import com.nancy.m6project.repositories.status.StatusRepositoties;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.like.StatusLikeService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepositoties statusRepositoties;

    @Autowired
    FriendRequestRepositories friendRequestRepositories;

    @Autowired
    StatusLikeService statusLikeService;

    @Autowired
    CommentService commentService;


    @Override
    public List<Status> findAll() {
        return null;
    }

    @Override
    public List<Status> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Status> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Status findById(Long id) {
        return statusRepositoties.findById(id).get();
    }

    @Override
    public Status save(Status status) {
        return statusRepositoties.save(status);
    }

    @Override
    public Status update(Status model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {

        //Search and delete all foreign key at statusLike
        Iterable<StatusLike> statusLikes = statusLikeService.findAllByStatus_Id(id);
        if(statusLikes!= null){
            this.statusLikeService.deleteAllStatusLikeByStatus_id(statusLikes);
        }

        //Search and delete all foreign key at comment
        Iterable<Comment> comments = commentService.findAllByStatus_Id(id);
        if(comments!= null){
            this.commentService.deleteAllCommentByStatus_Id(comments);
        }

        statusRepositoties.deleteById(id);
        Status status = statusRepositoties.findById(id).orElse(null);
        return status == null;
    }

    @Override
    public Status findOne(Long id) {
        return statusRepositoties.findById(id).get();
    }

    @Override
    public List<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id) {
        return statusRepositoties.findStatusByAccount_IdOrderByCreateDateDesc(id);
    }

    @Override
    public List<Status> getAllStatusByAccountId(Long id, Integer total_record) {
        return statusRepositoties.findStatusesByAccountId(id, total_record);
    }



    @Override
    public Iterable<Status> findAllByContentContainingAndAccount_Id(String keyword, Long id) {
        return  statusRepositoties.findAllByContentContainingAndAccount_Id(keyword, id);
    }

    @Override
    public List<Status> getNewFeed(Long id,Integer total_record) {
        return statusRepositoties.getNewFeed(id,total_record);
    }

    @Override
    public List<Status> updateNewFeed(Long id, Integer total_record) {
        return statusRepositoties.updateNewFeed(id,total_record);
    }
}
