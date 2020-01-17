package com.imooc.sucurity.core.validate.code;

import com.imooc.sucurity.core.constant.SecurityConstant;
import com.imooc.sucurity.core.properties.SecurityProperties;
import com.imooc.sucurity.core.properties.SmsCodeProperties;
import com.imooc.sucurity.core.validate.code.bean.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 放在过滤器链的前面,用来过滤校验一些验证码相关的业务,简单的配置一下就可以了
 **/
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private SecurityProperties securityProperties;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(),ValidateCodeType.SMS);
        urlMap.put(SecurityConstant.PASSWORD_LOGIN_URL, ValidateCodeType.IMAGE);
        urlMap.put(SecurityConstant.MOBILE_LOGIN_URL, ValidateCodeType.SMS);
    }

    private void addUrlToMap(String url , ValidateCodeType type) {
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
        for (String u : urls) {
            urlMap.put(u, type);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型为" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response), type);
                logger.info("验证码校验通过");
            } catch (ValidateCodeException e) {
                failureHandler.onAuthenticationFailure(request, response, e);
                return;//错误了就结束了.不到下一个Filter
            }
        }


        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        Set<String> urls = urlMap.keySet();
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                result = urlMap.get(url);
                break;
            }
        }
        return result;
    }
}
