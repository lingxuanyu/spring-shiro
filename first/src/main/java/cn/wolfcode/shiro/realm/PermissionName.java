package cn.wolfcode.shiro.realm;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


//retention������������  target����ע�����õ�λ�� 
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface PermissionName {

	String value();
}
