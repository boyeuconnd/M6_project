package com.nancy.m6project.model.response;

import com.nancy.m6project.model.status.Status;
import lombok.Data;


@Data
public class NewFeedResponse {
    private boolean isLike;

    private Status status;
}
