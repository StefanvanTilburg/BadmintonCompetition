package nl.competitie.badminton.BadmintonCompetitie.repository;

import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Haalt competities uit de database en schrijft competities naar de database
 */
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
}
