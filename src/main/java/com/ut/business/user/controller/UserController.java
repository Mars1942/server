package com.ut.business.user.controller;

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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page<User> firstPage(@RequestParam("pageNumber") int pageNumber){

        return userService.findAll(pageNumber, Constant.PAGE_SIZE);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {

        return userService.findByLoginNameAndPassWord(user.getName(),user.getPassWord());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("user") User user){

        return userService.save(user);
    }
}
