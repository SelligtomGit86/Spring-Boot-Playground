package com.example.demotest.controller;

import com.example.demotest.model.Event;
import com.example.demotest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/events")
@Controller
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/all")
    public String findAll(Model model){
        List<Event> events = eventRepository.findAll();

        model.addAttribute("events", events);
        return "events/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id, Model model) {
        Optional<Event> user = eventRepository.findById(id);

        if(user.isPresent()){
            int isDeleted = eventRepository.deleteById(id);

            if(isDeleted == 1){
                return "event/delete_success";
            }
            return "event/delete_error";
        }else{
            return "errors/event_not_found";
        }
    }

}
