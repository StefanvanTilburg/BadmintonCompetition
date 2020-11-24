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
 *
 * TODO : - The CascadeType.All removes all competitions when I remove 1 competition. Because all clubs get deleted,
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
    @JoinTable(name = "competition_club",
            joinColumns = @JoinColumn(name = "competition_Id", referencedColumnName = "competitionId"),
            inverseJoinColumns = @JoinColumn(name = "club_Id", referencedColumnName = "clubId"))
    private Set<Club> clubs = new HashSet<>();

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

    public Set<Club> getClubs() {
        return clubs;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }

    // TODO Create view for this function
    public void addClub(Club club) {
        this.clubs.add(club);
        //club.getCompetitions().add(this);
    }

    public void removeClub(Club club) {
        this.clubs.remove(club);
        //club.getCompetitions().remove(club);
    }

    public boolean hasClub(Club club) {
        for (Club competitionClub: getClubs()) {
            if (competitionClub.getClubId() == club.getClubId()) {
                return true;
            }
        }
        return false;
    }
}
