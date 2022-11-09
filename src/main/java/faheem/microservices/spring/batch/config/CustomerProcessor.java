package faheem.microservices.spring.batch.config;

import faheem.microservices.spring.batch.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CustomerProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer customer) {
        log.info("processing the Customer item...");
            if(customer.getCountry().equalsIgnoreCase("Brazil")){
                log.info("Replacing Brazil with Torino and changing firstName as well");
                customer.setCountry("Torino");
                customer.setFirstName("faheem");
            }

            return customer;

    }
}
