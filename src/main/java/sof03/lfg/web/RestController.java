package sof03.lfg.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sof03.lfg.domain.CategoryRepository;
import sof03.lfg.domain.Meeting;
import sof03.lfg.domain.MeetingRepository;
import sof03.lfg.domain.UserRepository;

@CrossOrigin
@Controller
public class RestController {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    
        // RESTful service to get all meetings
    @RequestMapping(value = "/meetings", method = RequestMethod.GET)
    public @ResponseBody List<Meeting> meetingListRest() {
        return (List<Meeting>) meetingRepository.findAll();
    }

    // RESTful service to get meeting by id
    @RequestMapping(value = "/meetings/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Meeting> findMeetingRest(@PathVariable("id") Long meetingId) {
        return meetingRepository.findById(meetingId);
    }
}
