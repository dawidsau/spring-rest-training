package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import lombok.Data;
import pl.sauermann.spring.rest.training.restwithguru.rest.BaseEntity;

import javax.persistence.Entity;

@Data
@Entity
public class Vendor extends BaseEntity {

    private String text;
}
