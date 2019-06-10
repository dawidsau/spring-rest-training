
package pl.sauermann.spring.rest.training.restwithguru.users.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {

    private String street;
    private String city;
    private String state;
    private String postcode;

}
