package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    public static final CustomerMapper CUSTOMER_MAPPER = CustomerMapper.INSTANCE;
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CUSTOMER_MAPPER);
    }

    @Test
    public void shouldGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Dawid");
        customer1.setLastName("s");
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer2.setFirstName("Dawid2");
        customer2.setLastName("s2");
        customer2.setId(2L);

        List<Customer> customerList = Lists.newArrayList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOList = customerService.getCustomerList();

        assertEquals(2, customerDTOList.size());

    }

    @Test
    public void shouldFindCustomerById() {
        Customer customer = new Customer();
        customer.setLastName("aa");
        customer.setFirstName("BB");
        customer.setId(1L);

        when(customerRepository.findCustomerByFirstName("BB")).thenReturn(customer);

        CustomerDTO customerByFirstName = customerService.findCustomerByFirstName("BB");

        assertEquals("aa", customerByFirstName.getLastName());
    }


    @Test
    public void shouldCreateNewCustomer() {
        Customer customer = new Customer();
        customer.setId(10L);
        customer.setFirstName("Nowy");

        CustomerDTO customerDTO = CUSTOMER_MAPPER.customerToCustomerDTO(customer);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO result = customerService.createNewCustomer(customerDTO);

        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals("/api/customers/10", result.getCustomerUrl());

    }

    @Test
    public void shouldUpdateExistingUser() {
        Customer customer = new Customer();
        customer.setId(10L);
        customer.setFirstName("Stary");
        customer.setLastName("Stare_Nazwisko");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("UpdatedName");
        customerDTO.setLastName("UpdatedLast");

        Customer returnCustomer = new Customer();
        returnCustomer.setId(10L);
        returnCustomer.setFirstName("UpdatedName");
        returnCustomer.setLastName("UpdatedLast");

        when(customerRepository.findById(10L)).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.save(any())).thenReturn(returnCustomer);

        CustomerDTO resultDTO = customerService.updateUser(10L, customerDTO);
        assertEquals("UpdatedName", resultDTO.getFirstName());
    }

    @Test
    public void shouldDeleteCustomer() {
        customerService.deleteById(10L);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}