package com.nancy.m6project.model.response;


import com.nancy.m6project.model.comment.Comment;
import lombok.Data;

@Data
public class CommentResponse {
        private boolean isLike;
        private Comment comment;
}
