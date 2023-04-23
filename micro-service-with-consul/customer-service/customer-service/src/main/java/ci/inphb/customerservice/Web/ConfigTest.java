package ci.inphb.customerservice.Web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConfigTest {

    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;
    @Value("${customer.params.x}")
    private String x;
    @Value("${customer.params.x}")
    private String y;

    @GetMapping(path = "/test")
    public Map<String, String> params(){
        return Map.of("P1",p1,"P2",p2,"X",x, "Y",y);
    }
}
