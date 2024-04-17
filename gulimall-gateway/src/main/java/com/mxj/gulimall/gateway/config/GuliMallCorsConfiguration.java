package com.mxj.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Title: GuliMallCorsConfiguration
 * @Author mi xiu jin
 * @Package com.mxj.gulimall.gateway.config
 * @Date 2024/4/16 16:20
 * @description: 跨域配置
 */
@Configuration
public class GuliMallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter(){
        //跨域配置信息
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(corsConfigurationSource);
    }
}
