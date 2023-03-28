package com.example.demotest.controller;

import com.example.demotest.model.User;
import com.example.demotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model model){

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            model.addAttribute("user", user.get());
            return "user/user";
        }
        return "home/index";
    }

    @GetMapping("/all")
    public String findAll(Model model){
        List<User> users = userRepository.findAll();

        model.addAttribute("users", users);
        return "user/userslist";
    }

}