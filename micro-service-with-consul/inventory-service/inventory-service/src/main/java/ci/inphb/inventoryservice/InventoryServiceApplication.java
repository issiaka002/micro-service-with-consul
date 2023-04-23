package ci.inphb.inventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.stream.Stream;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
class Product{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private double price;
	private int quantity;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long>{}

@Projection(name = "fullproducts", types = Product.class)
interface ProductProjection{
	public Long getId();
	public String getName();
	public double getPrice();
	public int getQuatity();
}

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration configuration){

		return args -> {
			configuration.exposeIdsFor(Product.class);
			Stream.of("Computer HP", "Router CISCO","Imprimante 3D", "Jeu video GJ", "Bluetooth ecouteur", "Souris sans fil").forEach(des->{
				Product product=Product.builder()
						.description(des)
						.price(Math.random()*99090+ 10000)
						.quantity(Math.random()>0.5?156:990)
						.build();
				productRepository.save(product);
			});
			productRepository.findAll().forEach(product -> {
				System.out.println(product.getDescription());
			});
		};
	}

}
