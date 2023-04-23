package ci.inphb.orderservice.Domain;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
}
