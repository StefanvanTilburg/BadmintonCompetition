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
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer clubId;

    private String clubName;

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
