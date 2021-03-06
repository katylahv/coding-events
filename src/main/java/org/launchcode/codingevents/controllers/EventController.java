package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.EventType;
import org.launchcode.codingevents.models.Events;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }
    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute("event",new Events());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }
    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Events newEvent,
                              Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
            EventData.add(newEvent);
            return "redirect:"; // go to diff page
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(Model model, @PathVariable("id") int id){
        model.addAttribute("title","Edit Event Name (id=ID)");
        model.addAttribute("event",EventData.getById(id));
        return "events/edit" ; // display an edit form w this signature
    }

    @PostMapping("/edit/{id}")
    public String processEditForm(int id, String name, String description, Model model){
            EventData.getById(id).setName(name);
            EventData.getById(id).setDescription(description);
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

}
