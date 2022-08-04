package project1.doyouknow;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project1.doyouknow.argumenresolver.MemberCheckArgumentResolver;
import project1.doyouknow.interceptor.login.LoginInterceptor;

import java.util.List;

@Configuration
public class WebMVC implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberCheckArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).order(1).addPathPatterns("/**")
                .excludePathPatterns("/login","/*.ico","/css/**","/board/People","/board/Place","/board/Video"
                        ,"/join","/","/error","/images/**");
    }
}
