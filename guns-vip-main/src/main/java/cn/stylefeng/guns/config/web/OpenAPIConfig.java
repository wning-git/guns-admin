package cn.stylefeng.guns.config.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.fop.fo.pagination.Title;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "${spring.application.name}",
                version = "1.0.0",
                description = "This is a sample server quanyihui api server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.",
                termsOfService = "http://369qyh.com/",
                license = @io.swagger.v3.oas.annotations.info.License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = {
                @Server(
                        url = "http://localhost",
                        description = "本地"
                ),
                @Server(
                        url = "http://192.169.1.10:8081",
                        description = "测试"
                ),
                @Server(
                        url = "http://testApi.369qyh.com",
                        description = "预发"
                ),
                @Server(
                        url = "http://api.369qyhl.com",
                        description = "生产"
                )
        },
        tags = {
                @Tag(name = "1-Header：" + HttpHeaders.AUTHORIZATION, description = "登录之后获取的JWT Token，类型是bearer"),
                @Tag(name = "1-Header：Accept-Language", description = "国际化语言：zh-CN（中文），en-US（英文）")
        }
)
// 安全配置：JWT Token。也可以配置其他类型的鉴权，比如：basic
@SecuritySchemes(
        @io.swagger.v3.oas.annotations.security.SecurityScheme(
                name = HttpHeaders.AUTHORIZATION,
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "basic"
        )
)

@Configuration
public class OpenAPIConfig {
    /*@Bean
    @Profile("!prod")
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder().group("Actuator")
                .pathsToMatch("/actuator/**")
                .pathsToExclude("/actuator/health/*")
                .build();
    }*/

/*    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info().title("权易汇 API").version(appVersion).description(
                        "This is a sample server quanyihui api server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }*/
}
