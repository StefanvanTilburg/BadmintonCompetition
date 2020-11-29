package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.model.Team;
import nl.competitie.badminton.BadmintonCompetitie.model.Competition;
import nl.competitie.badminton.BadmintonCompetitie.repository.TeamRepository;
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
    TeamRepository teamRepository;

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

    @GetMapping("/addCompetitionTeam/{competitionId}")
    protected String addTeam(@PathVariable("competitionId") Integer competitionId, Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("competition", competitionRepository.findById(competitionId));
        return "addCompetitionTeam";
    }

    @GetMapping("/competition/{competitionId}/teams")
    protected String competitionAddTeam(@PathVariable("competitionId") Integer competitionId,
                                        @RequestParam Integer teamId, Model model) {
        Optional<Team> team = teamRepository.findById(teamId);
        Optional<Competition> competition = competitionRepository.findById(competitionId);

        if (competition.isPresent()) {
            if (!competition.get().hasTeam(team.get())) {
                competition.get().getTeams().add(team.get());

                competitionRepository.save(competition.get());
                model.addAttribute("competition", competitionRepository.findById(competitionId));
                model.addAttribute("teams", teamRepository.findAll());
                return "redirect:/competition";
            }
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
