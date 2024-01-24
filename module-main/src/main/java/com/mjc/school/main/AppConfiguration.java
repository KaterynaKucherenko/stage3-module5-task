package com.mjc.school.main;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.mjc.school.*"})
@EntityScan(basePackages = {"com.mjc.school.repository.model.AuthorModel", "com.mjc.school.repository.model.NewsModel", "com.mjc.school.repository.model.TagModel"})
@EnableAspectJAutoProxy
public class AppConfiguration {
}

