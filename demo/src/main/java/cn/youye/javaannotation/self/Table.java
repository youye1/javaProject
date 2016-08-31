package cn.youye.javaannotation.self;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现table注解
 * Created by pc on 2016/8/31.
 */
@Target({ElementType.TYPE})
//作用域为类或接口
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
