package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.UsernameValidator;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * lcd  2019/12/30
 * Description:
 */
public class User {
    /**
     * 1.声明视图
     **/
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};
    private int id;
    @UsernameValidator(message = "测试注解校验的做法")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
    @JsonView(UserSimpleView.class)//2,get方法指定视图
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonView(UserSimpleView.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
