package com.ut.business.application.controller;

import com.ut.business.application.domain.Application;
import com.ut.business.application.service.ApplicationService;
import com.ut.business.common.BackResult;
import com.ut.business.pagingandsorting.constant.Constant;
import com.ut.business.user.domain.User;
import com.ut.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BackResult firstPage(@RequestParam("pageNumber") int pageNumber) throws Exception {
        Page<Application> page = applicationService.findAll(pageNumber, Constant.PAGE_SIZE);
        BackResult<Page<Application>> br = new BackResult<>(page);
        return br;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BackResult findById(@PathVariable String id) throws Exception{
        Application user = applicationService.findById(id);
        BackResult<Application> br = new BackResult<>(user);
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BackResult del(@PathVariable String id) throws Exception{
        applicationService.del(id);
        BackResult<String> br = new BackResult<>("");
        br.setMsg("删除成功");
        return br;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public BackResult add(@RequestBody Application application) throws Exception{
        BackResult<String> br = new BackResult<>(applicationService.save(application));
        br.setMsg("添加成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BackResult update(@PathVariable String id,@RequestBody Application application) throws Exception{
        application.setId(id);
        BackResult<String> br = new BackResult<>(applicationService.save(application));
        br.setMsg("修改成功");
        return br;
    }
}
