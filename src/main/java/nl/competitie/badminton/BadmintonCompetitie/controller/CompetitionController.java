package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Stuurt de views voor een competitie aan
 */
@Controller
public class CompetitionController {

    @Autowired
    CompetitionRepository competitionRepository;

    @GetMapping({"/", "/Competition"})
    protected String showCompetition(Model model) {
        model.addAttribute("allCompetitions", competitionRepository.findAll());
        return "competitionOverview";
    }
}
