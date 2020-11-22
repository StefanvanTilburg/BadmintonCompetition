package nl.competitie.badminton.BadmintonCompetitie;

import nl.competitie.badminton.BadmintonCompetitie.model.Club;
import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import nl.competitie.badminton.BadmintonCompetitie.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Stefan van Tilburg
 *
 * Info on CommandLineRunner. Using it for now to fill database with Many-To-Many relationship values. Trying to figure
 * out how to make it work with HTML > POST method.
 *
 * // TODO : Figure out POST form with Many-To-Many relationship in HTML view
 * // TODO : Remove CommandLineRunner implementation
 * // TODO : Is a default constructor commen practise in Spring-MVC modelling?
 *
 * Source CommandLineRunner : https://www.youtube.com/watch?v=-q9rp2pzvGU&ab_channel=JavaGuides
 */
@SpringBootApplication
public class BadmintonCompetitieApplication implements CommandLineRunner {

	@Autowired
	private CompetitionRepository competitionRepository;

	public static void main(String[] args) {
		SpringApplication.run(BadmintonCompetitieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Competition competition = new Competition("DUC");
		Competition competition1 = new Competition("Bond");

		Club club = new Club("AFC Appelscha");
		Club club1 = new Club("Smash Hoogeveen");
		Club club2 = new Club("Smilde Badminton");
		Club club3 = new Club("Racketeers");

		competition.addClub(club);
		competition.addClub(club1);
		competition.addClub(club3);

		competition1.addClub(club1);
		competition1.addClub(club2);
		competition1.addClub(club3);

		club.getCompetitions().add(competition);
		club1.getCompetitions().add(competition);
		club1.getCompetitions().add(competition1);
		club2.getCompetitions().add(competition1);
		club3.getCompetitions().add(competition);
		club3.getCompetitions().add(competition1);

		this.competitionRepository.save(competition);
		this.competitionRepository.save(competition1);
	}
}
