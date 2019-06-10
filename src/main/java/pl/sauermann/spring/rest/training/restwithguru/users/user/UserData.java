package pl.sauermann.spring.rest.training.restwithguru.users.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    private List<User> data = new ArrayList<>();
}
