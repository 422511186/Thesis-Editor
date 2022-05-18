package com.cmgzs.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置tomcat，允许特殊字符传入
 *
 * @Auther: hzy
 * @Date: 2022/4/28 20:47
 * @Description:
 */

@Configuration
public class TomcatConfig {

    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}&"));
        return factory;
    }

}
