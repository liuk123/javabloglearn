package com.lk.fishblog;

import com.lk.fishblog.controller.PerformanceInteceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

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
				.addPathPatterns("/api/article/**")
				.addPathPatterns("/api/comment/**")
				.addPathPatterns("/api/user/**")
				.addPathPatterns("/api/reply/**");
	}

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
//		return builder -> {
//			builder.indentOutput(true);
//			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//		};
//	}
}
