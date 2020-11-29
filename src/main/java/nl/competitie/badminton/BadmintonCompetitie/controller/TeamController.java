package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.model.Team;
import nl.competitie.badminton.BadmintonCompetitie.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 *
 * Stuurt de views voor een club aan
 */
@Controller
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/team")
    protected String showTeams(Model model) {
        model.addAttribute("allTeams", teamRepository.findAll());
        model.addAttribute("team", new Team());
        return "teamOverview";
    }

    @PostMapping("/team/add")
    protected String saveOrUpdateTeam(@ModelAttribute("club") Team team, BindingResult result) {
        if (!result.hasErrors()) {
            teamRepository.save(team);
        }
        return "redirect:/team";
    }
}
