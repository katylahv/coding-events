package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Events;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "events/create";
    }
    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute Events newEvent){
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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable("id") int id){
        //model.addAttribute("title","Edit Event Name (id=ID)");
        model.addAttribute("events",EventData.getById(id));
        return "events/edit/{id}" ; // display an edit form w this signature
    }

//    @PostMapping("edit/{id}")
//    public String processEditForm(int eventId, String name, String description){
//        if (EventData.getById(eventId) != null){
//            EventData.getById(eventId).setName(name);
//            EventData.getById(eventId).setDescription(description);
//        }
//        return "redirect:";
//    }
}
