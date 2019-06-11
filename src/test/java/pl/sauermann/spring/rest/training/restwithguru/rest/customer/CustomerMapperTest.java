package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void shouldMapCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Dawid");
        customer.setLastName("SS");
        customer.setId(1L);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals("Dawid",customerDTO.getFirstName());
    }
}