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

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public BackResult findAll() throws Exception {
        Iterable<Role> page = roleService.findAll();
        BackResult<Iterable<Role>> br = new BackResult<>(page);
        return br;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BackResult findById(@PathVariable String id) throws Exception{
        Role role = roleService.findById(id);
        BackResult<Role> br = new BackResult<>(role);
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BackResult del(@PathVariable String id) throws Exception{
        roleService.del(id);
        BackResult<String> br = new BackResult<>("");
        br.setMsg("删除成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public BackResult add(@RequestBody Role role) throws Exception{
        BackResult<String> br = new BackResult<>(roleService.save(role, role.getApplicationIds()));
        br.setMsg("添加成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BackResult update(@PathVariable String id, @RequestBody Role role) throws Exception{
        role.setId(id);
        BackResult<String> br = new BackResult<>(roleService.update(role, role.getApplicationIds()));
        br.setMsg("修改成功");
        return br;
    }
}
