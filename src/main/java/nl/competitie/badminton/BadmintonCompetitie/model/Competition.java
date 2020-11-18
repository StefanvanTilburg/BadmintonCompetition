package nl.competitie.badminton.BadmintonCompetitie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Representatie van een competitie
 */
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer competitionId;


}
