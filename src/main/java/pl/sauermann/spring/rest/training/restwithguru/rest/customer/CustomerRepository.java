package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByFirstName(String name);
}
