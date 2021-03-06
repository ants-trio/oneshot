package happyhouse_team02.land;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import happyhouse_team02.land.landservice.web.util.argumentresolver.LoginMemberArgumentResolver;
import happyhouse_team02.land.landservice.web.util.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginMemberArgumentResolver());
	}

 	@Override
 	public void addInterceptors(InterceptorRegistry registry){
 		registry.addInterceptor(new LoginCheckInterceptor())
 			.order(1)
			.addPathPatterns("/posts/new", "/bookmark/**");
 	}
}
