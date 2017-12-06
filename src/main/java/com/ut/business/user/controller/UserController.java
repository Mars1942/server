package com.ut.business.user.controller;

import com.ut.business.common.BackResult;
import com.ut.business.pagingandsorting.constant.Constant;
import com.ut.business.user.domain.User;
import com.ut.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BackResult firstPage(@RequestParam("pageNumber") int pageNumber) throws Exception {
        Page<User> page = userService.findAll(pageNumber, Constant.PAGE_SIZE);
        BackResult<Page<User>> br = new BackResult<>(page);
        return br;
    }


    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public BackResult first1Page(@RequestParam("pageNumber") int pageNumber) throws Exception{
        Page<User> page = userService.findAll(pageNumber, Constant.PAGE_SIZE);
        BackResult<Page<User>> br = new BackResult<>(page);
        return br;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public BackResult del(@RequestParam("id") String id) throws Exception{
        userService.del(id);
        BackResult<String> br = new BackResult<>("删除成功");
        return br;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BackResult login(@RequestBody User user) throws Exception{
        User us = userService.findByLoginNameAndPassWord(user.getName(),user.getPassWord());
        BackResult<User> br = new BackResult<>(us);
        if (us == null) {
            br.setMsg("没有该用户或密码不正确");
            return br;
        }
        return br;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public BackResult add(@RequestParam("user") User user) throws Exception{
        BackResult<String> br = new BackResult<>(userService.save(user));
        return br;
    }
}
