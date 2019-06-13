package pl.sauermann.spring.rest.training.restwithguru.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sauermann.spring.rest.training.restwithguru.rest.category.Category;
import pl.sauermann.spring.rest.training.restwithguru.rest.category.CategoryRepository;
import pl.sauermann.spring.rest.training.restwithguru.rest.customer.Customer;
import pl.sauermann.spring.rest.training.restwithguru.rest.customer.CustomerRepository;
import pl.sauermann.spring.rest.training.restwithguru.rest.vendor.Vendor;
import pl.sauermann.spring.rest.training.restwithguru.rest.vendor.VendorRepository;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initCategoryData();
        initCustomerData();
        initVendorData();
    }

    private void initVendorData() {
        Vendor vendor = new Vendor();
        vendor.setText("First text");
        Vendor vendor2 = new Vendor();
        vendor2.setText("Some text");

        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);
        log.info("Vendor data inserted in amount: " + vendorRepository.count());

    }

    private void initCustomerData() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Dawid");
        customer.setLastName("Aniol");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Donald");
        customer2.setLastName("Trump");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("Harry");
        customer3.setLastName("Crishna");

        Customer customer4 = new Customer();
        customer4.setId(4L);
        customer4.setFirstName("Somebody");
        customer4.setLastName("Else");

        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        log.info("Customer data inserted in amount: " + customerRepository.count());

    }

    private void initCategoryData() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        log.info("Category data inserted in amount: " + categoryRepository.count());

    }
}
