package cn.wolfcode.shiro.realm;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


//retention决定生命周期  target决定注解作用的位置 
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface PermissionName {

	String value();
}
