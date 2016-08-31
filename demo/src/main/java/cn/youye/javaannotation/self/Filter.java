package cn.youye.javaannotation.self;

/**
 * 1.实体类
 * Created by pc on 2016/8/31.
 */
@Table("test")
public class Filter {
    @Column("id")
    private String id;
    @Column("user_name")
    private String userName;
    @Column("nick_name")
    private String nickName;
    @Column("age")
    private int age;
    @Column("city")
    private String city;
    @Column("email")
    private String email;
    @Column("mobile")
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
