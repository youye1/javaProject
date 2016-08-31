package cn.youye.javaannotation.self;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现column注解
 * Created by pc on 2016/8/31.
 */
@Target({ElementType.FIELD})
//作用域为字段
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();
}
