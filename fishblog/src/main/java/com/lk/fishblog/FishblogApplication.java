package com.lk.fishblog;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Comment;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class FishblogApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(FishblogApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {}
}
