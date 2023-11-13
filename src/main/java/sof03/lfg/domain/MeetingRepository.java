package sof03.lfg.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {

    List<Meeting> findByTitle(String title);

}
