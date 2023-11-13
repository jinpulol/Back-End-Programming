package sof03.lfg;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sof03.lfg.domain.Category;
import sof03.lfg.domain.CategoryRepository;
import sof03.lfg.domain.Meeting;
import sof03.lfg.domain.MeetingRepository;

@SpringBootApplication
public class LfgApplication {	// LFG = Looking For Group

	private static final Logger log = LoggerFactory.getLogger(LfgApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LfgApplication.class, args);
	}

	@Bean
	public CommandLineRunner meetingDemo(MeetingRepository meetingRepository, CategoryRepository categoryRepository) {

		SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy"); // date format

		return (args) -> {

			// Add your own examples here, for example:
			// Adding a few categories
			log.info("Save a few categories");
			Category cat1 = new Category("Kulttuuri ja viihde");
			Category cat2 = new Category("Urheilu ja liikunta");
			Category cat3 = new Category("Ruoka ja juoma");
			Category cat4 = new Category("Lauta- ja videopelit");

			categoryRepository.save(cat1);
			categoryRepository.save(cat2);
			categoryRepository.save(cat3);
			categoryRepository.save(cat4);

			// Adding a few meetings
			log.info("Save a few meetings");
			Meeting meeting1 = new Meeting("Kävelyretki", "Espoo", time.parse("01.01.2024"), "Kävelyretki Nuuksiossa", cat2);
			Meeting meeting2 = new Meeting("Elokuvailta", "Helsinki", time.parse("04.02.2024"), "Elokuvailta Tennispalatsissa", cat1);
			Meeting meeting3 = new Meeting("Kahvittelua", "Jyväskylä", time.parse("05.03.2024"), "Kahvia ja juttelua yliopiston kahvilassa", cat3);
			Meeting meeting4 = new Meeting("Lautapelailua", "Tampere", time.parse("16.02.2024"), "Dungeon & Dragons -peliä ja herkuttelua", cat4);

			meetingRepository.save(meeting1);
			meetingRepository.save(meeting2);
			meetingRepository.save(meeting3);
			meetingRepository.save(meeting4);

			// Fetch all categories
			log.info("Fetch all categories");
			for(Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			// Fetch all meetings
			log.info("Fetch all meetings");
			for(Meeting meeting : meetingRepository.findAll()) {
				log.info(meeting.toString());
			}


		};
	}

}
