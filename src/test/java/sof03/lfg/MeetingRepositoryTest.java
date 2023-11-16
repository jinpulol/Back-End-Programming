package sof03.lfg;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.lfg.domain.Meeting;
import sof03.lfg.domain.MeetingRepository;
import sof03.lfg.domain.Category;
import java.text.ParseException;




@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MeetingRepositoryTest {



    @Autowired
    private MeetingRepository meetingrepository;

    @Test // check if title matches with location
    public void findByTitleShouldEqualLocation() {
        List<Meeting> events = meetingrepository.findByTitle("Elokuvailta");
        
        assertThat(events).hasSize(1);
        assertThat(events.get(0).getLocation()).isEqualTo("Helsinki");
    }
    
    @Test // check if new event is created
    public void createNewEvent() {
        SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy"); // date format
        Category cat1 = new Category("category1");
        Meeting meeting = null;
        try {
            meeting = new Meeting("Pitsinnypläystä", "Lapua", time.parse("22.03.2024"), "Tule pitsinnypläyskurssille", cat1, "tepatsu");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        meetingrepository.save(meeting);
        assertThat(meeting.getMeetingId()).isNotNull();
    }

}