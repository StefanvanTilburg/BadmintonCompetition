package nl.competitie.badminton.BadmintonCompetitie.repository;

import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Haalt competities uit de database en schrijft competities naar de database
 */
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
    Optional<Competition> findByCompetitionName(String competitionName);
}
