package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.HttpResponse;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class StatusApiController {

    @Autowired
    AccountService accountService;

    @Autowired
    StatusService statusService;

    @PostMapping("api/{id}/create-status")
    public HttpResponse create(@RequestBody @Validated Status status, @PathVariable Long id) {
        HttpResponse response = new HttpResponse();
        status.setAccount(this.accountService.findOne(id));
        if (statusService.save(status) != null) {
            response.setMessage("success");
        } else {
            response.setMessage("fail");
        }
        return response;
    }

    @GetMapping("api/statuses/{id}")
    public Iterable<Status> listStatus(@PathVariable Long id) {
        Iterable<Status> list = statusService.findStatusByAccount_IdOrderByCreateDateDesc(id);
        for (Status status : list) {
            status.getAccount().setPassword("hidden");
        }
        return list;
    }

    @DeleteMapping("/api/statuses/{id}/delete")
    public ResultResponse deleteStatus(@PathVariable Long id) {
        ResultResponse resultResponse = new ResultResponse();
        try{
            Status status = statusService.findById(id);
            statusService.delete(id);
            resultResponse.setMessage("xóa thành công");
        }catch (Exception e){
            resultResponse.setMessage("fail");
        }

        return resultResponse;
    }
}
