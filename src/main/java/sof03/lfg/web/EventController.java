package sof03.lfg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sof03.lfg.domain.CategoryRepository;
import sof03.lfg.domain.MeetingRepository;



@Controller
public class EventController {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/events")
    public String events(Model model) {
        model.addAttribute("meetings", meetingRepository.findAll());

        return "eventlist";
    }


}
