package com.dynamic.routing.config

import com.dynamic.routing.interceptor.DataSourceInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig (var dataSourceInterceptor: DataSourceInterceptor): WebMvcConfigurer{
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(dataSourceInterceptor).addPathPatterns("/**")
    }
}