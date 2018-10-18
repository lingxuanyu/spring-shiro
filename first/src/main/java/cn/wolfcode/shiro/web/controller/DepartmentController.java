package cn.wolfcode.shiro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @RequestMapping("")
    public String index() throws  Exception{
        System.out.println("index......");
        return "department";
    }
    @RequestMapping("/save")
    public String save() throws  Exception{
        System.out.println("save......");
        return "department";
    }

	    @RequestMapping("/edit")
	    public String edit() throws  Exception{
	        System.out.println("edit.....");
	        return "department";
	    }

    @RequestMapping("/delete")
    public String delete() throws  Exception{
        System.out.println("delete.....");
        return "department";
    }
}
