package uk.gov.hmrc.sample.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration()
@ComponentScan(basePackages = {"uk.gov.hmrc.sample.controller"})
public class TestConfiguration {
    //Define your mock beans here
}