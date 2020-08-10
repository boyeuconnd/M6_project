package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;


@RestController
public class StatusApiController {

    @Autowired
    StatusService statusService;

    @PostMapping("api/create-status")
    public String create(@RequestBody @Validated Status status){
        String message = "";
        try {
            if (status.getContent() != null){
                statusService.save(status);
                message = "Đăng status thành công !!";
            }else {
                message = "Lỗi chưa nhập nội dung !!!";
            }
        }catch (Exception e){
            message = "Lỗi !!";
        }
        return message;
    }
}
