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
public class Team {

    public static final String DEFAULT_TEAM = "DEFAULT TEAM";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    private String teamName;

    @ManyToMany(mappedBy = "teams")
    private Set<Competition> competitions = new HashSet<>();

    public Team() {
        this(DEFAULT_TEAM);
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer clubId) {
        this.teamId = clubId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String clubName) {
        this.teamName = clubName;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}
