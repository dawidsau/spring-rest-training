
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
public class Job implements Serializable {

    private String title;
    private String company;
}
