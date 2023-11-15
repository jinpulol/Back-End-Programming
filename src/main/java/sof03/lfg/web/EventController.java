package sof03.lfg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sof03.lfg.domain.CategoryRepository;
import sof03.lfg.domain.Meeting;
import sof03.lfg.domain.MeetingRepository;

// Controller for meetings
@Controller
public class EventController {

    // Injecting repositories
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Index page
    @GetMapping("/")
    public String index() {
        return "index";
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
    public String saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
        return "redirect:events";
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

}
