package pl.sauermann.spring.rest.training.restwithguru.rest.customer;

import lombok.Data;
import pl.sauermann.spring.rest.training.restwithguru.rest.BaseEntity;

import javax.persistence.Entity;

@Data
@Entity
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;

}
