package com.sxx.sivir;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@ComponentScan(basePackages = { "com.sxx.sivir.core.dal", "com.sxx.sivir.core.service","com.sxx.sivir.core.common","com.sxx.sivir.web"})
@MapperScan(basePackages = {"com.sxx.sivir.core.dal.dao"})
@EnableSwagger2Doc
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class },scanBasePackages = "com.sxx.sivir")
public class SivirApplication {
    private final static Logger logger = LoggerFactory.getLogger(SivirApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SivirApplication.class, args);
        logger.info("--- Sivir started ---> http://localhost:8070/");
        logger.info("--- Swagger is online ---> http://localhost:8070/swagger-ui.html");
    }

}
