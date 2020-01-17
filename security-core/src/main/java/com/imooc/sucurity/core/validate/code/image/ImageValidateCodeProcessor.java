package com.imooc.sucurity.core.validate.code.image;

import com.imooc.sucurity.core.validate.code.ValidateCode;
import com.imooc.sucurity.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * lcd  2020/1/15
 * Description:
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor {
    @Override
    public void send(ValidateCode code, ServletWebRequest request)  {
        try {
            ImageIO.write(((ImageCode) code).getImage(), "JPEG", request.getResponse().getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("输出图片错误");
        }
    }


}
