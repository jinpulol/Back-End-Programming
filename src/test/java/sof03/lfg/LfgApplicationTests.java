package sof03.lfg;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.lfg.web.EventController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LfgApplicationTests {

	@Autowired
	private EventController eventController;
	
	@Test
	public void contextLoads() {
		assertThat(eventController).isNotNull();
	}

}
