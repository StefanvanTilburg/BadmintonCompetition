package nl.competitie.badminton.BadmintonCompetitie.service;

import nl.competitie.badminton.BadmintonCompetitie.repository.BadmintonCompetitionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Service
public class BadmintonCompetitionUserDetailsService implements UserDetailsService {

    @Autowired
    BadmintonCompetitionUserRepository badmintonCompetitionUserRepository;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return badmintonCompetitionUserRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("Gebruiker " + s + " is niet gevonden.")
        );
    }
}
