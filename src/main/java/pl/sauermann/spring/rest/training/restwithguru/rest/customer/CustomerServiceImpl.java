package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import org.springframework.stereotype.Service;
import pl.sauermann.spring.rest.training.restwithguru.rest.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        return customerRepository
                .findAll()
                .stream()
                .map(c -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(c);
                    customerDTO.setCustomerUrl(CustomerController.BASE_URL+ "/" + c.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerByFirstName(String name) {
        Customer customer = customerRepository.findCustomerByFirstName(name);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(CustomerController.BASE_URL+"/" + customer.getId());
        return customerDTO;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl(CustomerController.BASE_URL+ "/" + customer.getId());
            return customerDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl(CustomerController.BASE_URL+"/" + savedCustomer.getId());
        return returnDTO;
    }

    @Override
    public CustomerDTO updateUser(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO patchUser(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        if (customerDTO.getFirstName() != null) {
            customer.setFirstName(customerDTO.getFirstName());
        }
        if (customerDTO.getLastName() != null) {
            customer.setLastName(customerDTO.getLastName());
        }
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
