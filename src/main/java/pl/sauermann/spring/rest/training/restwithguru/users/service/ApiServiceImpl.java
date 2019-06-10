package pl.sauermann.spring.rest.training.restwithguru.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sauermann.spring.rest.training.restwithguru.users.user.User;
import pl.sauermann.spring.rest.training.restwithguru.users.user.UserData;

import java.util.List;


@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<User> getUsers(Integer limit) {
        UserData userData = restTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class);
        return userData.getData();
    }
}
