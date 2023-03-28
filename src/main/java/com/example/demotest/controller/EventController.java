package com.example.demotest.controller;

import com.example.demotest.model.Event;
import com.example.demotest.model.User;
import com.example.demotest.repository.EventRepository;
import com.example.demotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
