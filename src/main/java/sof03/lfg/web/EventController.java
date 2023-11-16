package sof03.lfg.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sof03.lfg.domain.CategoryRepository;
import sof03.lfg.domain.Meeting;
import sof03.lfg.domain.MeetingRepository;
import sof03.lfg.domain.User;
import sof03.lfg.domain.UserRepository;

// Controller for meetings
@Controller
public class EventController {

    // Injecting repositories
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // Index page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Register page
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // Show all meetings
    @GetMapping("/events")
    public String events(Model model) {
        model.addAttribute("meetings", meetingRepository.findAll());

        return "eventlist";
    }

    // Add new meeting
    @GetMapping("/add")
    public String addMeeting(Model model) {
        model.addAttribute("meeting", new Meeting());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addmeeting";
    }

    // Save new meeting
    @PostMapping("/savemeeting")
    public String saveMeeting(@Valid @ModelAttribute("meeting") Meeting meeting, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("meeting", meeting);
            return "addmeeting";
        } else {
            meetingRepository.save(meeting);
            return "redirect:events";
        }

    }

    // Delete meeting
    @GetMapping("/deletemeeting/{id}")
    public String deleteMeeting(@PathVariable("id") Long meetingId, Model model) {
        meetingRepository.deleteById(meetingId);
        return "redirect:../events";
    }

    // Edit meeting
    @GetMapping("/editmeeting/{id}")
    public String editMeeting(@PathVariable("id") Long meetingId, Model model) {
        model.addAttribute("meeting", meetingRepository.findById(meetingId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editmeeting";
    }


    // Vaiheessa, attendees täytyy lisätä meetingiin...
    // // Join meeting
    // @PostMapping("/join/{id}")
    // public String joinMeeting(@PathVariable("id") Long meetingId, Principal principal) {
    //     Meeting meeting = meetingRepository.findById(meetingId)
    //     User user = userRepository.findByUsername(principal.getName());
    //     meeting.getAttendees().add(user);
    //     meetingRepository.save(meeting);
    //     return "redirect:/events";
    // }

    // // Cancel meeting
    // @PostMapping("/cancel/{id}")
    // public String cancelMeeting(@PathVariable("id") Long meetingId, Principal principal) {
    //     Meeting meeting = meetingRepository.findById(meetingId)
    //     User user = userRepository.findByUsername(principal.getName());
    //     meeting.getAttendees().remove(user);
    //     meetingRepository.save(meeting);
    //     return "redirect:/events";
    // }

}
