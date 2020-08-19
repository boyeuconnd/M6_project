package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.HttpResponse;
import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.model.img.Img;
import com.nancy.m6project.model.response.NewFeedResponse;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import com.nancy.m6project.service.img.ImgService;
import com.nancy.m6project.service.like.StatusLikeService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.NewThreadAction;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@RestController
public class StatusApiController {

    public static final int PUBLIC = 0;
    public static final int FRIEND = 1;
    public static final int PRIVATE = 2;

    @Autowired
    AccountService accountService;

    @Autowired
    StatusService statusService;

    @Autowired
    CommentService commentService;

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    StatusLikeService statusLikeService;

    @Autowired
    ImgService imgService;

    @PostMapping("api/{id}/create-status")
    public HttpResponse create(@RequestBody Status status, @PathVariable Long id) throws SQLIntegrityConstraintViolationException {
        List<Img> imgList = new ArrayList<>();
        HttpResponse response = new HttpResponse();
        if (status.getImages() != null) {
            Img img = status.getImages().get(0);
            imgList.add(imgService.save(img));
            status.setImages(imgList);
        } else {
            status.setImages(null);
        }
        status.setAccount(this.accountService.findOne(id));
        if (statusService.save(status) != null) {
            response.setMessage("success");
        } else {
            response.setMessage("fail");
        }
        return response;
    }

    @GetMapping("api/statuses/{id}")
    public List<NewFeedResponse> listStatus(@PathVariable Long id) {
        List<NewFeedResponse> newFeedResponseList = new ArrayList<>();
        List<Status> statusList = statusService.findStatusByAccount_IdOrderByCreateDateDesc(id);
        List<Long> statusLikedId = statusLikeService.getAllStatusLikedIdByAccountId(id);
        for (Status status : statusList) {
            NewFeedResponse newFeedResponse = new NewFeedResponse();
            newFeedResponse.setLike(statusLikedId.contains(status.getId()));
            newFeedResponse.setStatus(status);
            newFeedResponseList.add(newFeedResponse);
        }
        return newFeedResponseList;
    }

    @DeleteMapping("/api/statuses/{id}/delete")
    public ResultResponse deleteStatus(@PathVariable Long id) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            statusService.delete(id);
            resultResponse.setMessage("xóa thành công");
        } catch (Exception e) {
            resultResponse.setMessage("fail");
        }

        return resultResponse;
    }

    @GetMapping("/api/newfeed/{current_id}/{total_record}")
    public List<Status> getNewFeedSimple(@PathVariable Long current_id, @PathVariable Integer total_record) {
        List<Status> newFeedList = statusService.getNewFeed(current_id,total_record);
        return newFeedList;
    }

    @PatchMapping("api/find-status/{id}")
    public Iterable<Status> findStatus(@RequestBody String keyword, @PathVariable Long id) {
        Iterable<Status> listResult = statusService.findAllByContentContainingAndAccount_Id(keyword, id);
        return listResult;
    }

    @GetMapping("/api/{status_id}/liked")
    public List<Account> getAllAccountLikedStatus(@PathVariable Long status_id) {
        return accountService.getAllAccountLikedThisStatus(status_id);
    }

    @GetMapping("/api/get-one-status/{id}")
    public Status getOneStatus(@PathVariable Long id) {
        return statusService.findOne(id);
    }

    @GetMapping("/api/newfeed2/{current_id}/{total_record}")
    public List<NewFeedResponse> getNewFeedResponse(@PathVariable Long current_id,@PathVariable Integer total_record) {
        List<NewFeedResponse> newFeedResponseList = new ArrayList<>();
        List<Status> statusList;

        if (friendRequestService.checkHaveFriend(current_id)) {
            statusList = statusService.getNewFeed(current_id,total_record);
        } else {
            statusList = statusService.getAllStatusByAccountId(current_id,total_record);
        }
        List<Long> statusLikedId = statusLikeService.getAllStatusLikedIdByAccountId(current_id);

        for (Status status : statusList) {
            NewFeedResponse newFeedResponse = new NewFeedResponse();
            newFeedResponse.setLike(statusLikedId.contains(status.getId()));
            newFeedResponse.setStatus(status);
            newFeedResponseList.add(newFeedResponse);
        }
        return newFeedResponseList;
    }

    @PutMapping("api/edit-status")
    public ResponseEntity<Status> editStatus(@RequestBody Status status) {
        List<Img> img = new ArrayList<>();
        img = status.getImages();
        status.setImages(img);
        statusService.save(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
