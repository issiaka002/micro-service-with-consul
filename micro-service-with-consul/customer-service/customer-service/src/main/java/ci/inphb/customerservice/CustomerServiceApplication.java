package ci.inphb.customerservice;

import ci.inphb.customerservice.Domain.Customer;
import ci.inphb.customerservice.Repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration configuration){
		return args -> {
			configuration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(List.of(
					Customer.builder().name("yao maurice").email("yao@gmail.com").build(),
					Customer.builder().name("koffi koffi").email("koffi@gmail.com").build(),
					Customer.builder().name("jean jack").email("jean@gamil.com").build(),
					Customer.builder().name("Maurice duboir").email("duboir@gmail.com").build(),
					Customer.builder().name("kkadi").email("kkadi@gmail.com").build(),
					Customer.builder().name("dubois jean").email("dubois@gamil.com").build()
			));
			customerRepository.findAll().forEach(customer -> System.out.println(customer.getName()));
		};
	}

}
