package com.imooc.sucurity.core.validate.code;

import java.time.LocalDateTime;

/**
 * lcd  2020/1/15
 * Description:
 */
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    //比如接受一个60秒,创建一个比当前时间多60秒的时间点,相当于再这个时间点就过期无效了
    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpire() {
        return expireTime.isBefore(LocalDateTime.now());//有效期小于当前时间,说明false(无效过期了)
    }
}
