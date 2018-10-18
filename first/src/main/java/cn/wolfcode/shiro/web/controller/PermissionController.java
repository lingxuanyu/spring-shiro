package cn.wolfcode.shiro.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import cn.wolfcode.shiro.dao.IPermissionDAO;
import cn.wolfcode.shiro.domain.Permission;
import cn.wolfcode.shiro.realm.PermissionName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class PermissionController {

    @Autowired
    private RequestMappingHandlerMapping rmhm;
    
    @Autowired
    private IPermissionDAO iPermissionDao;

    @RequestMapping("/reload")
    public String reload() throws  Exception{
    	//获取requestMapping()的标签
    	Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
    	Collection<HandlerMethod> values = handlerMethods.values();
    	
    	List<String> allResource = iPermissionDao.getAllResource();
    	
    	for(HandlerMethod hd:values) {
    		//获取到permission
    		RequiresPermissions methodAnnotation = hd.getMethodAnnotation(RequiresPermissions.class);
    		if(methodAnnotation != null) {
    			String source = methodAnnotation.value()[0];
    			if(allResource.contains(source)) {
    				continue;
    			}
    			Permission permission = new Permission();
    			permission.setName(hd.getMethodAnnotation(PermissionName.class).value());
    			permission.setResource(source);
    			//获取@requirePermission()权限操作
    			iPermissionDao.save(permission);
    		}
    	}
        return "main";
    }

}
