package com.example.demotest.controller;

import com.example.demotest.model.Event;
import com.example.demotest.model.User;
import com.example.demotest.repository.EventRepository;
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

    private final EventRepository eventRepository;

    @Autowired
    public UserController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
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

        Event e = new Event();
        e.setEventType("ADDING_USER");
        e.setEventName("Nouvel utilisateur ajouté : " + user.getUsername());
        e.setEventDesc("Un nouvel utilisateur vient de s'inscrire !");

        eventRepository.addEvent(e);

        return "user/registration_success";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id, Model model) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            int isDeleted = userRepository.deleteUser(id);

            if(isDeleted == 1){

                Event e = new Event();
                e.setEventType("DELETING_USER");
                e.setEventName("Utilisateur supprimé : " + user.get().getUsername());
                e.setEventDesc("Suppression d'un nouvel utilisateur");

                eventRepository.addEvent(e);

                model.addAttribute("user", user.get());
                return "user/delete_success";
            }
            return "user/delete_error";
        }else{
            return "errors/user_not_found";
        }
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable int id, Model model) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/update_form";
        }
        return "errors/user_not_found";
    }

    @PostMapping("/update/submit")
    public String updateUserSubmission(@ModelAttribute("user") User user, Model model) {
        Optional<User> user1 = userRepository.findById(user.getId());

        if(user1.isPresent()){
            int isDeleted = userRepository.updateUser(user.getUsername(), user.getFirstname(), user.getLastname(), user.getId());

            if(isDeleted == 1){
                model.addAttribute("user", user);
                return "user/delete_success";
            }
            return "user/delete_error";
        }else{
            return "errors/user_not_found";
        }
    }



}