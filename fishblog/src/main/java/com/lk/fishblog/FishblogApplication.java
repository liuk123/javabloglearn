package com.lk.fishblog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
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

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Value("${upload.path}")
	private String uploadPath;
	@Value("${upload.temPath}")
	private String uploadTemPath;
	@Value("${upload.resource}")
	private String resource;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")){
			registry.addResourceHandler("/"+uploadPath+"**").addResourceLocations("file:D:/code/java/javabloglearn/fishblog/assets/" + uploadPath);
			registry.addResourceHandler("/"+uploadTemPath+"**").addResourceLocations("file:D:/code/java/javabloglearn/fishblog/assets/" + uploadTemPath);
			registry.addResourceHandler("/"+resource+"**").addResourceLocations("file:D:/code/java/javabloglearn/fishblog/assets/" + resource);
		}else{
			registry.addResourceHandler("/"+uploadPath+"**").addResourceLocations("file: /home/assets/" + uploadPath);
			registry.addResourceHandler("/"+uploadTemPath+"**").addResourceLocations("file: /home/assets/" + uploadTemPath);
			registry.addResourceHandler("/"+resource+"**").addResourceLocations("file: /home/assets/" + resource);
		}
	}
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
			builder.indentOutput(true);
			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		};
	}
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		ObjectMapper mapper = converter.getObjectMapper();
//		Hibernate5Module hibernate5Module = new Hibernate5Module();
//		mapper.registerModule(hibernate5Module);
//		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//		return converter;
//	}
}
