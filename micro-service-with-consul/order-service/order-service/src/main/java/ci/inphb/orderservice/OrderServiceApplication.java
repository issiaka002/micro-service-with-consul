package ci.inphb.orderservice;

import ci.inphb.orderservice.Domain.Customer;
import ci.inphb.orderservice.Domain.Order;
import ci.inphb.orderservice.Domain.Product;
import ci.inphb.orderservice.Domain.ProductItem;
import ci.inphb.orderservice.Enum.OrderStatus;
import ci.inphb.orderservice.Repositories.OrderRepository;
import ci.inphb.orderservice.Repositories.ProductItemRepository;
import ci.inphb.orderservice.Services.CustomerRestClientService;
import ci.inphb.orderservice.Services.InventoryRestClientService;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients //.. Activer openFeign
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Customer> customers=customerRestClientService.allCostumers().getContent().stream().toList();
			List<Product> products=inventoryRestClientService.allProducts().getContent().stream().toList();

			customers.forEach(cus->{
				System.out.println(cus.getName());
			});
			System.out.println("=================================");
			products.forEach(pro->{
				System.out.println(pro.getDescription());
			});

			//...
			Random random=new Random();
			for (int i = 0; i < 20; i++) {
				Order order= Order.builder()
						.customer(customers.get(random.nextInt(customers.size())))
						.createdAt(new Date())
						.status(OrderStatus.CREATED)
						.build();
				Order savedOrder = orderRepository.save(order);
				for (int j = 0; j < products.size(); j++) {
					if(Math.random()>0.7){
						ProductItem productItem=ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.price(products.get(j).getPrice())
								.quantity(random.nextInt(15))
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);
					}
				}
				
			}


		};
	}

}
