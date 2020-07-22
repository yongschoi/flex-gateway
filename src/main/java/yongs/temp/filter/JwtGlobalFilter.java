package yongs.temp.filter;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

public class JwtGlobalFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(JwtGlobalFilter.class);
    private static String ACCESS_TOKEN = "access-token";
    private static final String imagePath = "/displayImg";
	private final String secretKey = "ThisIsMySecretKey";	
	
	@Override
	// - 우선순위 높음(HIGHEST_PRECEDENCE: -2147483648)
	// + 우선순위 낮음(LOWEST_PRECEDENCE:   2147483648)
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	ServerHttpRequest req = exchange.getRequest();
    	
    	String authorizationToken = null;
    	try {   	
    		// 이미지 display인 경우 header가 아닌 query로 token을 체크
    		if(req.getURI().getPath().contains(imagePath)) {
    			authorizationToken = req.getQueryParams().get(ACCESS_TOKEN).get(0);   	
    		// 기본적으로 header에 있는 token을 체크
    		} else {
    			authorizationToken = req.getHeaders().get(ACCESS_TOKEN).get(0);
    		}
    	} catch (NullPointerException ne) {
    		logger.debug("500 err >>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
    	}
    		
    	logger.debug("access-token >>> " + authorizationToken);
    	
    	if (!validateToken(authorizationToken)) {
            return onError(exchange, HttpStatus.UNAUTHORIZED);
        }
        return chain.filter(exchange);
    }
	
    private boolean validateToken(String jwt) {
    	if(jwt != null) {
    		try{
    			Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
    			return true;            
            } catch (Exception e) {
            	return false;
            }   		
    	} else {
    		return false;
    	}
    }
 
    private String generateKey(){
    	return Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }
    
	private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus)  {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        
        return response.setComplete();
    }
}