package ci.inphb.orderservice.Web;


import ci.inphb.orderservice.Domain.Customer;
import ci.inphb.orderservice.Domain.Order;
import ci.inphb.orderservice.Domain.Product;
import ci.inphb.orderservice.Repositories.OrderRepository;
import ci.inphb.orderservice.Repositories.ProductItemRepository;
import ci.inphb.orderservice.Services.CustomerRestClientService;
import ci.inphb.orderservice.Services.InventoryRestClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderRestController {
    private OrderRepository orderRepository;                            //..Donnee dans la base de donnee
    private ProductItemRepository productItemRepository;               //..Donnee dans la base de donnee
    private CustomerRestClientService customerRestClientService;      //..Donnee du micro-service <customer>
    private InventoryRestClientService inventoryRestClientService;    //..Donnee du micro-service <inventory>

    @GetMapping(path = "/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();   //.. verification sautée
        Customer customer=customerRestClientService.costumerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pr->{
            Product product=inventoryRestClientService.productById(pr.getProductId());
            pr.setProduct(product);
        });
        return order;
    }
}









