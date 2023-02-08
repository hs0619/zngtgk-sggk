package com.baosight.sggk;

import com.baosight.iplat4j.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "com.baosight")
@ServletComponentScan("com.baosight.iplat4j.core.web.servlet")
@ImportResource(locations = {"classpath*:spring/framework/platApplicationContext*.xml","classpath*:spring/framework/applicationContext*.xml"})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class HBGLApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HBGLApplication.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HBGLApplication.class);
    }

}
