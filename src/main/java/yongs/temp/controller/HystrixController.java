package yongs.temp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yongs.temp.vo.Employee;
import yongs.temp.vo.Product;

@RestController
@RequestMapping("/fallback")
public class HystrixController {	
	@GetMapping("/todo")
    public ResponseEntity<Mono<String>> todo() {
        return new ResponseEntity<Mono<String>>(Mono.just("service busy"), HttpStatus.REQUEST_TIMEOUT);
    }

	@GetMapping("/employee")
    public ResponseEntity<Flux<Employee>> employee() {
		Employee e1 = new Employee();
		e1.setId("1");
		e1.setName("홍길동1");
		e1.setLevel("senior");
		e1.setProject("Project-RED");
		e1.setSalary(5000);
		e1.setSkill("SWA");

		Employee e2 = new Employee();
		e2.setId("2");
		e2.setName("홍길동2");
		e2.setLevel("junior");
		e2.setProject("Project-WHITE");
		e2.setSalary(4500);
		e2.setSkill("DBA");

		Employee e3 = new Employee();
		e3.setId("3");
		e3.setName("홍길동3");
		e3.setLevel("junior");
		e3.setProject("Project-BLUE");
		e3.setSalary(4500);
		e3.setSkill("TA");

		Employee e4 = new Employee();
		e4.setId("4");
		e4.setName("홍길동4");
		e4.setLevel("senior");
		e4.setProject("Project-BLACK");
		e4.setSalary(5500);
		e4.setSkill("TA");
		
		Employee e5 = new Employee();
		e5.setId("5");
		e5.setName("홍길동5");
		e5.setLevel("junior");
		e5.setProject("Project-BLACK");
		e5.setSalary(4000);
		e5.setSkill("TA");				
		
        return new ResponseEntity<Flux<Employee>>(Flux.just(e1, e2, e3, e4, e5), HttpStatus.OK);
    }
	
	@GetMapping("/product")
    public ResponseEntity<Flux<Product>> product() {
		Product e1 = new Product();
		e1.setId("product01");
		e1.setName("초코바");
		e1.setImage("가나.png");
		e1.setPrice("500");
		
		Product e2 = new Product();
		e2.setId("product02");
		e2.setName("냉장고");
		e2.setImage("냉장고.jpg");
		e2.setPrice("2300000");
		
		Product e3 = new Product();
		e3.setId("product03");
		e3.setName("맛있는 떡");
		e3.setImage("떡.png");
		e3.setPrice("5500");				
		
        return new ResponseEntity<Flux<Product>>(Flux.just(e1, e2, e3), HttpStatus.OK);
    }
}
