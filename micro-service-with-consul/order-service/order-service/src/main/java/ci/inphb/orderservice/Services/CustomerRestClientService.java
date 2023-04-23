package ci.inphb.orderservice.Services;

import ci.inphb.orderservice.Domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClientService {
    @GetMapping("/customers/{id}")
    Customer costumerById(@PathVariable Long id);
    @GetMapping("/customers")
    PagedModel<Customer> allCostumers();

}
