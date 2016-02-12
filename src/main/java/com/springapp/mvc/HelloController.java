package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@CrossOrigin(origins = "http://localhost:8070")
public class HelloController {
    @Autowired
    RestDAO serviceDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "Hello!");
        return "hello";
    }

    @RequestMapping(value = "/service/users", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<User> getAllUsers() {
        return serviceDAO.getAllUsers();
    }

	@RequestMapping(value = "/service/users/{id}", method = RequestMethod.GET, produces = "application/json;" +
            "charset=UTF-8")
    @ResponseBody
	public User getUser(@PathVariable int id) {
		return serviceDAO.getUserByID(id);
	}

    /*@RequestMapping(value = "/service/users/{name}", method = RequestMethod.GET, produces =
            "application/json;" +
            "charset=UTF-8")
    @ResponseBody
    public User getUserByName(@PathVariable String name) {
        return serviceDAO.getUserByName(name);
    }*/

    @RequestMapping(value = "/service/posts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Post> getAllPosts() {
        return serviceDAO.getAllPosts();
    }

    @RequestMapping(value = "/service/posts/{id}", method = RequestMethod.GET, produces = "application/json;" +
            "charset=UTF-8")
    @ResponseBody
    public Post getPost(@PathVariable int id) {
        return serviceDAO.getPostByID(id);
    }

    @RequestMapping(value = "service/my-posts", method = RequestMethod.GET, produces = "application/json;" +
        "charset=UTF-8")
    @ResponseBody
    public List<Post> getMyPosts(@AuthenticationPrincipal User user) {
        return serviceDAO.getAllUserPosts(user.getId());
    }

    @RequestMapping(value = "service/me", method = RequestMethod.GET, produces = "application/json;" +
        "charset=UTF-8")
    @ResponseBody
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }

}