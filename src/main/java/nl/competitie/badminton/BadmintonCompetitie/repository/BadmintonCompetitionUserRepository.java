package nl.competitie.badminton.BadmintonCompetitie.repository;

import nl.competitie.badminton.BadmintonCompetitie.model.BadmintonCompetitionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadmintonCompetitionUserRepository extends JpaRepository<BadmintonCompetitionUser, Integer> {
    Optional<BadmintonCompetitionUser> findByUsername(String name);
}
