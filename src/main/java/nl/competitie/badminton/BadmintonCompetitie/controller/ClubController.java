package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.model.Club;
import nl.competitie.badminton.BadmintonCompetitie.repository.ClubRepository;
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
public class ClubController {

    @Autowired
    ClubRepository clubRepository;

    @GetMapping("/club")
    protected String showClubs(Model model) {
        model.addAttribute("allClubs", clubRepository.findAll());
        model.addAttribute("club", new Club());
        return "clubOverview";
    }

    @PostMapping("/club/add")
    protected String saveOrUpdateClub(@ModelAttribute("club") Club club, BindingResult result) {
        if (!result.hasErrors()) {
            clubRepository.save(club);
        }
        return "redirect:/club";
    }
}
