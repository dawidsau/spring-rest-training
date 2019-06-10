package pl.sauermann.spring.rest.training.restwithguru.users.service;

import pl.sauermann.spring.rest.training.restwithguru.users.user.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
}
