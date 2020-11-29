package nl.competitie.badminton.BadmintonCompetitie.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Representatie van een competitie
 *
 * TODO : - The CascadeType.All removes all competitions when I remove 1 competition. Because all teams get deleted,
 *          it cascades back I presumme. Look up possibilities.
 */
@Entity
public class Competition {

    public static final String DEFAULT_COMPETITION = "DEFAULT COMPETITION";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competitionId;

    private String competitionName;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "competition_team",
            joinColumns = @JoinColumn(name = "competition_Id", referencedColumnName = "competitionId"),
            inverseJoinColumns = @JoinColumn(name = "team_Id", referencedColumnName = "teamId"))
    private Set<Team> teams = new HashSet<>();

    public Competition() {
        this(DEFAULT_COMPETITION);
    }

    public Competition(String competitionName) {
        this.competitionName = competitionName;
    }

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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    // TODO Create view for this function
    public void addTeam(Team team) {
        this.teams.add(team);
        //team.getCompetitions().add(this);
    }

    public void removeTeam(Team team) {
        this.teams.remove(team);
        //team.getCompetitions().remove(team);
    }

    public boolean hasTeam(Team team) {
        for (Team competitionTeam : getTeams()) {
            if (competitionTeam.getTeamId() == team.getTeamId()) {
                return true;
            }
        }
        return false;
    }
}
