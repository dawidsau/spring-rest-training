package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomerList();

    CustomerDTO findCustomerByFirstName(String name);

    CustomerDTO findCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateUser(Long id, CustomerDTO customerDTO);

    CustomerDTO patchUser(Long id, CustomerDTO customerDTO);

    void deleteById(Long id);
}
