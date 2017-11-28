package com.ut.business.user.controller;

import com.ut.business.pagingandsorting.constant.Constant;
import com.ut.business.user.domain.User;
import com.ut.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page<User> firstPage(@RequestParam("pageNumber") int pageNumber){

        return userService.findAll(pageNumber, Constant.PAGE_SIZE);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam("name") String name, @RequestParam("passWord") String passWord, HttpServletRequest request) {
        String a = request.getParameter("name");
        System.out.print(a);
        return userService.findByLoginNameAndPassWord(name,passWord);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("user") User user){

        return userService.save(user);
    }
}
