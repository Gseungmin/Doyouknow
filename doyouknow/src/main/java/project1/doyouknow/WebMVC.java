package project1.doyouknow;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project1.doyouknow.argumenresolver.LoginArgumentResolver;
import project1.doyouknow.interceptor.content.ContentPeopleInterceptor;
import project1.doyouknow.interceptor.content.ContentPlaceInterceptor;
import project1.doyouknow.interceptor.content.ContentVideoInterceptor;
import project1.doyouknow.interceptor.login.LoginInterceptor;
import project1.doyouknow.interceptor.myPage.MyPageInterceptor;
import project1.doyouknow.interceptor.upload.UploadPeopleInterceptor;
import project1.doyouknow.interceptor.upload.UploadPlaceInterceptor;
import project1.doyouknow.interceptor.upload.UploadVideoInterceptor;

import java.util.List;

@Configuration
public class WebMVC implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).order(1).addPathPatterns("/**")
                .excludePathPatterns("/login","/*.ico","/css/**","/board/People","/board/Place","/board/Video"
                        ,"/join","/","/error","/images/**"
                ,"/member/membership", "/posts/comments","/members/posts/comments","/members/posts/likes","/boards/posts"
                        ,"/member/vip/posts","/api/error/**","/posts/likes");
        registry.addInterceptor(new ContentPeopleInterceptor()).order(2)
                .addPathPatterns("/board/People");
        registry.addInterceptor(new ContentPlaceInterceptor()).order(3)
                .addPathPatterns("/board/Place");
        registry.addInterceptor(new ContentVideoInterceptor()).order(4)
                .addPathPatterns("/board/Video");
        registry.addInterceptor(new MyPageInterceptor()).order(5)
                .addPathPatterns("/myPage");
        registry.addInterceptor(new UploadPeopleInterceptor()).order(6)
                .addPathPatterns("/upload/person");
        registry.addInterceptor(new UploadPlaceInterceptor()).order(7)
                .addPathPatterns("/upload/place");
        registry.addInterceptor(new UploadVideoInterceptor()).order(8)
                .addPathPatterns("/upload/video");
    }
}
