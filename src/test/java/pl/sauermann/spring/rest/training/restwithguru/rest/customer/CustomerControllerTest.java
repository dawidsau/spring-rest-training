package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sauermann.spring.rest.training.restwithguru.rest.AbstractRestControllerTest;
import pl.sauermann.spring.rest.training.restwithguru.rest.RestResponseEntityExceptionHandler;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRST_NAME = "Dawid";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void shouldPassWithCustomersListSize() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("Dawid");
        customerDTO1.setLastName("dd");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO1.setFirstName("Dawid2");
        customerDTO1.setLastName("dd2");

        List<CustomerDTO> listDTO = Lists.newArrayList(customerDTO1, customerDTO2);

        when(customerService.getCustomerList()).thenReturn(listDTO);
        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void shouldEqualCustomerDTO() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName("dd");

        when(customerService.findCustomerByFirstName(FIRST_NAME)).thenReturn(customerDTO1);

        mockMvc.perform(get(CustomerController.BASE_URL+"/byName/Dawid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void shouldCreateNewCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Dawid");
        customer.setLastName("dd");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL+"/1");

        when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

        mockMvc.perform(post(CustomerController.BASE_URL+"/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Dawid")));

    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("UpdatedName");
        customerDTO.setLastName("UpdatedLast");
        customerDTO.setCustomerUrl(CustomerController.BASE_URL+"/10");

        when(customerService.updateUser(10L, customerDTO)).thenReturn(customerDTO);

        mockMvc.perform(put(CustomerController.BASE_URL+"/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("UpdatedName")));
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL+"/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteById(anyLong());

    }
}