package ci.inphb.orderservice.Domain;


import lombok.Data;


@Data
public class Product  {
    private Long id;
    private String description;
    private double price;
    private int quantity;
}
