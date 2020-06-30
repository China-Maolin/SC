package com.maolin.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: wangmaolin
 * @Date: 2020/6/29 11:32  跨域配置类
 */
@Configuration
public class CrosConfiguration {
    @Bean
    public CorsFilter corsFilter(){
        //初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://manage.leyou.com");//允许http://manage.leyou.com/跨域
        corsConfiguration.setAllowCredentials(true);//允许跨域的域名如果要携带cookie，不能写*  *代表所有的域名都可以跨域
        corsConfiguration.addAllowedMethod("*");//*代表所有的请求方法，包括Get Post Put Delete
        corsConfiguration.addAllowedHeader("*");//*允许携带任何头信息

        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        System.out.println("//////////////////////////////////////////////////////////////////////////");
        //返回一个CorsFilter对象，需要一个参数CorsConfigurationSource
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
/* 或者在application.yml的spring: 下编写如下配置：
cloud:
        gateway:
        globalcors:
        add-to-simple-url-handler-mapping: true
        corsConfigurations:
        '[/**]':
        allowedOrigins:
        - "http://manage.leyou.com"
        allowedHeaders:
        - "*"
        allowCredentials: true
        maxAge: 360000
        allowedMethods:
        - GET
        - POST
        - DELETE
        - PUT
        - OPTIONS
        - HEAD
*/
