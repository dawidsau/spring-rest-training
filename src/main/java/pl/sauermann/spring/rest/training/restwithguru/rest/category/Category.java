package pl.sauermann.spring.rest.training.restwithguru.rest.category;

import lombok.Data;
import pl.sauermann.spring.rest.training.restwithguru.rest.BaseEntity;

import javax.persistence.Entity;

@Data
@Entity
public class Category extends BaseEntity {

    private String name;

}
