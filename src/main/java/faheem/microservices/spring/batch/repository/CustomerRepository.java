package faheem.microservices.spring.batch.repository;

import faheem.microservices.spring.batch.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
