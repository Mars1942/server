package com.ut.business.role.controller;

import com.ut.business.common.BackResult;
import com.ut.business.pagingandsorting.constant.Constant;
import com.ut.business.role.RoleService.RoleService;
import com.ut.business.role.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BackResult findPage(@RequestParam("pageNumber") int pageNumber) throws Exception {
        Page<Role> page = roleService.findAll(pageNumber, Constant.PAGE_SIZE);
        BackResult<Page<Role>> br = new BackResult<>(page);
        return br;
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public BackResult findById(@RequestParam("id") String id) throws Exception{
        Role role = roleService.findById(id);
        BackResult<Role> br = new BackResult<>(role);
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public BackResult del(@RequestParam("id") String id) throws Exception{
        roleService.del(id);
        BackResult<String> br = new BackResult<>("删除成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BackResult add(@RequestParam("role") Role role) throws Exception{
        BackResult<String> br = new BackResult<>(roleService.save(role));
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/updata", method = RequestMethod.POST)
    public BackResult updata(@RequestParam("role") Role role) throws Exception{
        BackResult<String> br = new BackResult<>(roleService.save(role));
        return br;
    }
}
