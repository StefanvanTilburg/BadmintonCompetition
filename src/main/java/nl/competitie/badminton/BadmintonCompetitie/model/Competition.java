package nl.competitie.badminton.BadmintonCompetitie.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Representatie van een competitie
 */
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competitionId;

    private String competitionName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "competition_club",
            joinColumns = @JoinColumn(name = "competition_Id", referencedColumnName = "competitionId"),
            inverseJoinColumns = @JoinColumn(name = "club_Id", referencedColumnName = "clubId"))
    private Set<Club> clubs = new HashSet<>();

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }
}
