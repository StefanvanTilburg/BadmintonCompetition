package nl.competitie.badminton.BadmintonCompetitie.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Representatie van een competitie
 */
@Entity
public class Club {

    public static final String DEFAULT_CLUB = "DEFAULT CLUB";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clubId;

    private String clubName;

    @ManyToMany(mappedBy = "clubs")
    private Set<Competition> competitions = new HashSet<>();

    public Club() {
        this(DEFAULT_CLUB);
    }

    public Club(String clubName) {
        this.clubName = clubName;
    }

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

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}
