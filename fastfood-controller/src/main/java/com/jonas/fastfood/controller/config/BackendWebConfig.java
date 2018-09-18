package com.jonas.fastfood.controller.config;

import com.jonas.fastfood.common.mvc.SpringMvc;
import com.jonas.fastfood.common.mvc.VersionRequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/**
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 * @see WebMvcConfigurationSupport
 * @see org.springframework.boot.autoconfigure.web
 */
@Configuration
public class BackendWebConfig extends WebMvcConfigurationSupport {

    private final BackendWebBean BackendWebBean;

    @Autowired
    public BackendWebConfig(BackendWebBean BackendWebBean) {
        this.BackendWebBean = BackendWebBean;
    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping();
    }

   @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
       /**配置swagger2的资源目录**/
       registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
       registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        SpringMvc.handlerFormatter(registry);
    }

   @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        SpringMvc.handlerConvert(converters);
    }

   /* @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        SpringMvc.handlerArgument(argumentResolvers);
    }*/

    /*@Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        SpringMvc.handlerReturn(returnValueHandlers);
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new BackendInterceptor(BackendWebBean)).addPathPatterns("/**");
        registry.addInterceptor(new BackendInterceptor(BackendWebBean)).addPathPatterns("*.jhtml");
    }
}
