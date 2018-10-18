package cn.wolfcode.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wolfcode.shiro.dao.IPermissionDAO;
import cn.wolfcode.shiro.dao.IRoleDAO;
import cn.wolfcode.shiro.dao.IUserDAO;
import cn.wolfcode.shiro.domain.User;

public class UserRealm extends AuthorizingRealm{

	@Autowired
	private IUserDAO iUserDao;
	@Autowired
	private IPermissionDAO iPermissionDao;
	@Autowired
	private IRoleDAO iRoleDao;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {

		String username = (String) token.getPrimaryPrincipal();
		User user = iUserDao.getUserByUsername(username);
		System.out.println("权限分配-userReaml.....");
		List<String> pers = new ArrayList<String>();
		List<String> roles = new ArrayList<String>();
		
		if("admin".equals(username)) {
			pers.add("*:*");
			roles = iRoleDao.getAllRoleSn();
		}
		if("zhangsan".equals(username)) {
			pers = iPermissionDao.getPermissionResourceByUserId(user.getId());
			roles = iRoleDao.getRoleSnByUserId(user.getId());
			
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(pers);
		info.addRoles(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = iUserDao.getUserByUsername(username);
		if(user == null) {
			return null;
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
				ByteSource.Util.bytes(user.getUsername()),getName());
		return info;
	}
	
	//清除缓存
	public void clearCached() {
	    PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
	    super.clearCache(principals);
	}
	
	
}
