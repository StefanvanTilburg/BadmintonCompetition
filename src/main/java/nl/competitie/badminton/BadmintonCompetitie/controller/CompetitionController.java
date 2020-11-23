package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.model.Club;
import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import nl.competitie.badminton.BadmintonCompetitie.repository.ClubRepository;
import nl.competitie.badminton.BadmintonCompetitie.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ClubRepository clubRepository;

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

    @GetMapping("/addCompetitionClub/{competitionId}")
    protected String addClub(@PathVariable("competitionId") Integer competitionId, Model model) {
        model.addAttribute("clubs", clubRepository.findAll());
        model.addAttribute("competition", competitionRepository.findById(competitionId));
        return "addCompetitionClub";
    }

    @GetMapping("/competition/{competitionId}/clubs")
    protected String competitionAddClub(@PathVariable("competitionId") Integer competitionId,
                                        @RequestParam Integer clubId, Model model) {
        Optional<Club> club = clubRepository.findById(clubId);
        Optional<Competition> competition = competitionRepository.findById(competitionId);

        if (competition.isPresent()) {
            if (!competition.get().hasClub(club.get())) {
                competition.get().getClubs().add(club.get());
            }
            competitionRepository.save(competition.get());
            model.addAttribute("competition", competitionRepository.findById(competitionId));
            model.addAttribute("clubs", clubRepository.findAll());
            return "redirect:/competition";
        }

        return "redirect:/competition";
    }

    @GetMapping("/delete/{competitionId}")
    protected String editRemoveCompetition(@PathVariable("competitionId") Integer competitionId, Model model) {
        Optional<Competition> competition = competitionRepository.findById(competitionId);
        if (!competition.isEmpty()) {
            competitionRepository.delete(competition.get());
        }
        return "redirect:/competition";
    }
}
