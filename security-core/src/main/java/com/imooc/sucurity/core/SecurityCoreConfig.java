package com.imooc.sucurity.core;

import com.imooc.sucurity.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * lcd  2020/1/6
 * Description:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
