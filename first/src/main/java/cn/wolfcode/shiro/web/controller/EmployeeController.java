package cn.wolfcode.shiro.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.shiro.realm.PermissionName;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping("")
    public String index() throws  Exception{
        return "employee";
    }
    @RequestMapping("/save")
    @RequiresPermissions("employee:save")
    @PermissionName("Ա���洢")
    public String save() throws  Exception{
        return "employee";
    }

    @RequestMapping("/edit")
    @PermissionName("Ա���༭")
    @RequiresPermissions("employee:edit")
    public String edit() throws  Exception{
        return "employee";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("employee:delete")
    @PermissionName("Ա��ɾ��")
    public String delete() throws  Exception{
        return "employee";
    }
}
