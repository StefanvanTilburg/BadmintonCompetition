package nl.competitie.badminton.BadmintonCompetitie;

import nl.competitie.badminton.BadmintonCompetitie.model.Club;
import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import nl.competitie.badminton.BadmintonCompetitie.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BadmintonCompetitieApplication {
	@Autowired
	private CompetitionRepository competitionRepository;

	public static void main(String[] args) {
		SpringApplication.run(BadmintonCompetitieApplication.class, args);
	}

	@Override
	public void run(String[] args) {
		// Create a couple of Competition and Club
		competitionRepository.save(new Competition("Competitie 1", new Club("Club A"), new Club("Club B")));
	}

}
