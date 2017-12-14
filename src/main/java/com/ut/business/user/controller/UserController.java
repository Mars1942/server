package com.ut.business.user.controller;

import com.ut.business.common.BackResult;
import com.ut.business.pagingandsorting.constant.Constant;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.user.domain.User;
import com.ut.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public BackResult firstList() throws Exception {
        List<User> list = userService.ListAll();
        BackResult<List<User>> br = new BackResult<>(list);
        return br;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BackResult findById(@PathVariable String id) throws Exception{
        User user = userService.findById(id);
        BackResult<User> br = new BackResult<>(user);
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BackResult del(@PathVariable String id) throws Exception{
        userService.del(id);
        BackResult<String> br = new BackResult<>("");
        br.setMsg("删除成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BackResult login(@RequestBody User user) throws Exception{
        User us = userService.findByLoginNameAndPassWord(user.getLoginName(),user.getPassWord());
        BackResult<User> br = new BackResult<>(us);
        if (us == null) {
            br.setCode(3);
            br.setMsg("没有该用户或密码不正确");
            return br;
        }
        br.setMsg("登录成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public BackResult add(@RequestBody User user) throws Exception{
        BackResult<String> br = new BackResult<>(userService.save(user,user.getRoleIds()));
        br.setMsg("添加成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BackResult update(@PathVariable String id,@RequestBody User user) throws Exception{
        user.setId(id);
        BackResult<String> br = new BackResult<>(userService.update(user,user.getRoleIds()));
        br.setMsg("修改成功");
        return br;
    }
}
