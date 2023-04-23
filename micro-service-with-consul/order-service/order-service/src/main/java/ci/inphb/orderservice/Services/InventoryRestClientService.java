package ci.inphb.orderservice.Services;

import ci.inphb.orderservice.Domain.Customer;
import ci.inphb.orderservice.Domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryRestClientService {
    @GetMapping("/products/{id}")
    Product productById(@PathVariable Long id);
    @GetMapping("/products")
    PagedModel<Product> allProducts();

}
