package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        HashMap<String,String> events = new HashMap<>();
        events.put("Code til Dawn", "whatever they do there");
        events.put("Hackathon", "some stuff");
        events.put("CoderGirl", "girls coding");
        model.addAttribute("events", events);
        return "events/index";
    }

}
