package com.example.demotest.controller;

import com.example.demotest.model.User;
import com.example.demotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "errors/user_not_found";
    }

    @GetMapping("/all")
    public String findAll(Model model){
        List<User> users = userRepository.findAll();

        model.addAttribute("users", users);
        return "user/userslist";
    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/add_user_form";
    }

    @PostMapping("/submitnew")
    public String addUser(@ModelAttribute("user") User user) {
        userRepository.addUser(user);
        return "user/registration_success";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id, Model model) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            int isDeleted = userRepository.deleteUser(id);

            if(isDeleted == 1){
                model.addAttribute("user", user.get());
                return "user/delete_success";
            }
            return "user/delete_error";
        }else{
            return "errors/user_not_found";
        }
    }


}