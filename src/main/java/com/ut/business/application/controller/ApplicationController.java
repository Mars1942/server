package com.ut.business.application.controller;

import com.ut.business.application.domain.Application;
import com.ut.business.application.domain.RoleToApplication;
import com.ut.business.application.service.ApplicationService;
import com.ut.business.common.BackResult;
import com.ut.business.pagingandsorting.constant.Constant;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.user.domain.User;
import com.ut.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "application")
public class ApplicationController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BackResult firstPage(Application application, @RequestParam("pageNumber") int pageNumber) throws Exception {
        Page<Application> page = applicationService.search(application,pageNumber, Constant.PAGE_SIZE);
        BackResult<Page<Application>> br = new BackResult<>(page);
        return br;
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public BackResult ListAll()throws Exception {
        List<Application> list = applicationService.listAll();
        BackResult<List<Application>> br = new BackResult<>(list);
        return br;
    }

    @RequestMapping(value = "/listByUser/{userId}", method = RequestMethod.GET)
    public BackResult ListByUser(@PathVariable String userId)throws Exception {
        User user = userService.findById(userId);
        List<Application> list = new ArrayList<Application>();
        if (user != null && user.getuToRList() != null) {
            for (UserToRole userToRole : user.getuToRList()) {
                if (userToRole.getRole().getrToAList() != null && userToRole.getRole().getrToAList() != null) {
                    for (RoleToApplication roleToApplication : userToRole.getRole().getrToAList()) {
                        if (roleToApplication.getApplication() != null) {
                            list.add(roleToApplication.getApplication());
                        }
                    }
                }
            }
        }
        BackResult<List<Application>> br = new BackResult<>(list);
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
