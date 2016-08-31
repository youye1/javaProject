package cn.youye.javaannotation.self;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 2.测试实现类
 * Created by pc on 2016/8/31.
 */
public class Test {

    public static void main(String[] args) {

        Filter f1 = new Filter();
        f1.setId("123456");

        Filter f2 = new Filter();
        f2.setUserName("lucy");
        f2.setAge(12);

        Filter f3 = new Filter();
        f3.setEmail("liu@example.com,zh@163.com,236@qq.com");

        String sql1 = query(f1);//查询指定Id的用户
        String sql2 = query(f2);//查询指定用户名的用户
        String sql3 = query(f3);//查询邮箱为其中任意一个的用户

        System.out.println("===="+sql1);
        System.out.println("===="+sql2);
        System.out.println("===="+sql3);
    }

    static String query(Filter filter) {
        StringBuffer sb = new StringBuffer();
        //解析注解
        //1.获取到class
        Class c = filter.getClass();
        //2.获取table名称;首先判断类中是否包含注解类
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("select * from ").append(tableName).append("where 1=1");
        //3.遍历字段，拼接查询条件
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Object fieldValue = null;
            String fieldName = null;
            //4.处理每个字段对应的sql,拿到字段名以及对应的值
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            } else {
                Column column = field.getAnnotation(Column.class);
                try {
                    String columnName = column.value();//获取字段的名称
                    //4.2通过反射获取字段的值
                    fieldName = field.getName();//获取字段名
                    //拼接获取字段的get方法名字
                    String columnGetMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    //获取字段的get方法
                    Method getMethod = c.getMethod(columnGetMethod);
                    //通过反射调用
                    fieldValue = getMethod.invoke(filter);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //拼装SQL
            //判断是否为int数据时并数值为0
            if (fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
                continue;
            }
            sb.append(" and ").append(fieldName);
            if (fieldValue instanceof String){
                if (((String) fieldValue).contains(",")){
                    String[] valueItems=((String) fieldValue).split(",");
                    sb.append(" in (");
                    for (String item:valueItems){
                        sb.append("'").append(item).append("'").append(",");
                    }
                    sb.append(")");
                }else {
                    sb.append("= '").append(fieldValue).append("'");
                }
            }else if (fieldValue instanceof Integer){
                sb.append("=").append(fieldValue);
            }
        }
        sb.append(";");
        return sb.toString();
    }
}
