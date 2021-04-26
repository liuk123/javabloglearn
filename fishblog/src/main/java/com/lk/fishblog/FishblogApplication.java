package com.lk.fishblog;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.lk.fishblog.controller.PerformanceInteceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableRedisHttpSession
public class FishblogApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(FishblogApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PerformanceInteceptor())
				.addPathPatterns("/article/**")
				.addPathPatterns("/comment/**")
				.addPathPatterns("/user/**")
				.addPathPatterns("/reply/**")
				.addPathPatterns("/file/**")
				.addPathPatterns("/admin/**");
	}

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Value("${upload.path}")
	private String uploadPath;
	@Value("${upload.temPath}")
	private String uploadTemPath;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")){
			registry.addResourceHandler("/"+uploadPath+"**").addResourceLocations("file:D:/code/java/javabloglearn/fishblog/" + uploadPath);
			registry.addResourceHandler("/"+uploadTemPath+"**").addResourceLocations("file:D:/code/java/javabloglearn/fishblog/" + uploadTemPath);
		}else{
			registry.addResourceHandler("/"+uploadPath+"**").addResourceLocations("file: /home/" + uploadPath);
			registry.addResourceHandler("/"+uploadTemPath+"**").addResourceLocations("file: /home/" + uploadTemPath);
		}
	}
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
			builder.indentOutput(true);
			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		};
	}
}
