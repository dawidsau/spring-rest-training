package pl.sauermann.spring.rest.training.restwithguru.users.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sauermann.spring.rest.training.restwithguru.users.service.ApiService;


@Controller
public class UserController {

    private ApiService apiService;

    @Autowired
    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, @RequestBody String limit) {
        if (limit != null) {
            model.addAttribute("users",
                    apiService.getUsers(new Integer(limit.split("=")[1])));
        } else {
            model.addAttribute("users", apiService.getUsers(10));
        }
        return "userList";
    }
}
