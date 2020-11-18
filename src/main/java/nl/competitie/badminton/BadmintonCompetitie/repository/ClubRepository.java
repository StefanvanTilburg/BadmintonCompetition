package nl.competitie.badminton.BadmintonCompetitie.repository;

import nl.competitie.badminton.BadmintonCompetitie.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Haalt clubs uit de database en schrijft clubs naar de database
 */
public interface ClubRepository extends JpaRepository<Club, Integer> {
}
