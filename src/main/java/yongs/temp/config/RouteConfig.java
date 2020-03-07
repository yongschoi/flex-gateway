package yongs.temp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import yongs.temp.filter.JwtGlobalFilter;

/* routing rule을 정의하는 방법(2가지)
 * 1. application.yml에서 설정하는 방법
 * 2. Java 클래스(예: RouteConfig)에서 Code로 정의하는 방법 
 */
@Configuration
public class RouteConfig {
    private static final Logger logger = LoggerFactory.getLogger(RouteConfig.class);
/*
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(p -> p.path("/test")
								 .uri("lb://flex-temp"))
					.route(p -> p.path("/delay")
								 .filters(f -> f.hystrix(c -> c.setName("tempcmd")
										 					   .setFallbackUri("forward:/fallback/temp")))
								 .uri("lb://flex-temp"))
				.build();
	}

  
	@LoadBalanced // @LoadBalanced가 없으면 docker 환경에서 eureka/zipkin call 이 안됨 
    @Bean  
    public RestTemplate restTemplate() {
		return new RestTemplate();
    }
*/	
    @Bean
    public GlobalFilter jwtFilter() {
        return new JwtGlobalFilter();
    }
}
