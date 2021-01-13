package com.zgq.plainarticle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 允许跨域
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        // 允许跨域访问资源定义： 所有资源
        corsRegistry.addMapping("/**")
                //只允许本地的9000端口访.allowedOrigins("http://localhost:9000", "http://127.0.0.1:9000")
                .allowedOrigins("*")
                // 允许发送Cookie
                .allowCredentials(true)
                // 允许所有方法
                .allowedMethods("GET", "POST","OPTION");
    }
}