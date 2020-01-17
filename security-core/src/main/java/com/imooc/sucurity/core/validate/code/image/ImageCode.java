package com.imooc.sucurity.core.validate.code.image;

import com.imooc.sucurity.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * lcd  2020/1/7
 * Description:
 */
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }
    //比如接受一个60秒,创建一个比当前时间多60秒的时间点,相当于再这个时间点就过期无效了
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
