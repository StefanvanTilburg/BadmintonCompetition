package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import nl.competitie.badminton.BadmintonCompetitie.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Stuurt de views voor een competitie aan
 */
@Controller
public class CompetitionController {

    @Autowired
    CompetitionRepository competitionRepository;

    @GetMapping({"/", "/competition"})
    protected String showCompetition(Model model) {
        model.addAttribute("allCompetitions", competitionRepository.findAll());
        return "competitionOverview";
    }

    @GetMapping("/competition/add")
    protected String showCompetitionForm(Model model) {
        model.addAttribute("competition", new Competition());
        return "competitionForm";
    }

    @GetMapping("/competition/{competitionName}")
    protected String showCompetitionDetails(Model model, @PathVariable("competitionName") String competitionName) {
        Optional<Competition> optionalCompetition = competitionRepository.findByCompetitionName(competitionName);
        if (optionalCompetition.isEmpty()) {
            return "redirect:/competition";
        }
        model.addAttribute("competition", optionalCompetition.get());
        return "competitionDetail";
    }

    @PostMapping("/competition/add")
    protected String saveOrUpdateCompetition(@ModelAttribute("competition") Competition competition,
                                             BindingResult result) {
        if (result.hasErrors()) {
            return "competitionForm";
        } else {
            competitionRepository.save(competition);
            return "redirect:/competition";
        }
    }
}
