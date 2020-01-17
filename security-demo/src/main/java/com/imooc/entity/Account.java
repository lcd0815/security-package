package com.imooc.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * lcd  2020/1/5
 * Description:
 */
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 用户名
    @Column
    private String username;
    // 密码
    @NotBlank
    private String password;
    //可用
    private boolean enabled=true;
    //过期
    private boolean expired=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
