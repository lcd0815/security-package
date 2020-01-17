package com.imooc.sucurity.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * lcd  2020/1/7
 * Description:
 * 根据随机数字生成一个图片
 * 对象存到session
 * 把图片相应给前端
 */
@RestController
public class ValidateController {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    @GetMapping("/code/{type}")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) {
        validateCodeProcessorMap.get(type + "ValidateCodeProcessor").create(new ServletWebRequest(request, response));
    }


}
