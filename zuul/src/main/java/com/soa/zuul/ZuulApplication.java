package com.soa.zuul;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2Doc
@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            List resources = new ArrayList<>();
            resources.add(swaggerResource("book-show-service", "/book-show-service/v2/api-docs", "2.0"));
            resources.add(swaggerResource("book-service", "/book-service/v2/api-docs", "2.0"));
            resources.add(swaggerResource("index-show-service","/index-show-service/v2/api-docs","2.0"));
            resources.add(swaggerResource("inventory-service","/inventory-service/v2/api-docs","2.0"));
            resources.add(swaggerResource("person-service","/person-service/v2/api-docs","2.0"));
            resources.add(swaggerResource("show-aggregation-service","/show-aggregation-service/v2/api-docs","2.0"));
            resources.add(swaggerResource("user-show-service","/user-show-service/v2/api-docs","2.0"));

            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }
}
