package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListDTO {

    private List<CustomerDTO> customers;
}
