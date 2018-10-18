package cn.wolfcode.shiro.web.controller;


import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest req) throws  Exception{
    	String errorAttribute = (String) req.getAttribute("shiroLoginFailure");
    	if(errorAttribute != null) {
    		if(UnknownAccountException.class.getName().equals(errorAttribute)) {
    			model.addAttribute("errInfo", "�˺Ų�����");
    		}else if(IncorrectCredentialsException.class.getName().equals(errorAttribute)) {
    			model.addAttribute("errInfo", "�û���/�������");
    		}else{
    			model.addAttribute("errInfo", "��������");
    		}
    	}
        return "forward:/login.jsp";
    }

}











