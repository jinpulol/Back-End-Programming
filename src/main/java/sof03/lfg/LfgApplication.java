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
import sof03.lfg.domain.User;
import sof03.lfg.domain.UserRepository;

@SpringBootApplication
public class LfgApplication { // LFG = Looking For Group

	private static final Logger log = LoggerFactory.getLogger(LfgApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LfgApplication.class, args);
	}

	@Bean
	public CommandLineRunner meetingDemo(MeetingRepository meetingRepository, CategoryRepository categoryRepository, UserRepository userRepository) {

		SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy"); // date format

		return (args) -> {

			// Add your own examples here, for example:

			// Adding a few users
			log.info("Save a few users");
			User user1 = new User("admin", "Olli", "Jokinen", 44, "$2a$10$26TIRHIYXikKZuz.g2cFwOUo5Hnr3KwC20Cvk4bFupP4a1LgHlt6S", "admin@lfg.app", "ADMIN");
			User user2 = new User("Eikka", "Ensio", "Esimerkki", 23, "$2a$10$YSX8kwvWvhLb17M25KMyY.uv3ckqJMrdu01si604nWaxvy1xxpBjW", "eikka@testi.test", "USER");
			User user3 = new User("tepatsu", "Teija", "Sukunimi", 32,"$2a$10$OZ8X71AqsVktyJoWBgI20uq4IfD4j7DDNqFwoOWmHNmVv.VwBjvsK", "tsukunimi@mail.mail", "USER");
			User user4 = new User("koolkid123", "Mikko", "Mallikas", 19, "$2a$10$VKSlKUJbCRp//GI5xIqfj.UV1/RSTlzDprNtAmhv6xMm9POi2uXti", "koolkidroks@mail.mail", "USER");

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);

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
			Meeting meeting1 = new Meeting("Kävelyretki", "Espoo", time.parse("19.1.2024"), "Kävelyretki Nuuksiossa",
					cat2, "Eikka");
			Meeting meeting2 = new Meeting("Elokuvailta", "Helsinki", time.parse("14.2.2024"),
					"Elokuvailta Tennispalatsissa", cat1, "tepatsu");
			Meeting meeting3 = new Meeting("Kahvittelua", "Jyväskylä", time.parse("1.3.2024"),
					"Kahvia ja juttelua yliopiston kahvilassa", cat3, "koolkid123");
			Meeting meeting4 = new Meeting("Lautapelailua", "Tampere", time.parse("20.4.2024"),
					"Dungeon & Dragons -peliä ja herkuttelua", cat4, "Eikka");

			meetingRepository.save(meeting1);
			meetingRepository.save(meeting2);
			meetingRepository.save(meeting3);
			meetingRepository.save(meeting4);

			// Fetch all categories
			log.info("Fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			// Fetch all meetings
			log.info("Fetch all meetings");
			for (Meeting meeting : meetingRepository.findAll()) {
				log.info(meeting.toString());
			}

		};
	}

}
