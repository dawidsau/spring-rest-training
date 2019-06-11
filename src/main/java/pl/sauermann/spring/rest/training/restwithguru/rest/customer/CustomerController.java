package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/customers";

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping
    public ResponseEntity<CustomerListDTO> showAllCustomersDTO() {
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(
                        customerService.getCustomerList()), HttpStatus.OK);
    }

    @RequestMapping("/byName/{firstName}")
    public ResponseEntity<CustomerDTO> getCustomerDTOByName(@PathVariable String firstName) {
        return new ResponseEntity<CustomerDTO>(
                customerService.findCustomerByFirstName(firstName), HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<CustomerDTO> getCustomerDTOById(@PathVariable String userId) {
        return new ResponseEntity<CustomerDTO>(
                customerService.findCustomerById(new Long(userId)), HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(
                customerService.updateUser(new Long(id), customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(
                customerService.patchUser(new Long(id), customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteById(new Long(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
