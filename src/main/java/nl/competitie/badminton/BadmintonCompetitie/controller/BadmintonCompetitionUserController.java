package nl.competitie.badminton.BadmintonCompetitie.controller;

import nl.competitie.badminton.BadmintonCompetitie.model.BadmintonCompetitionUser;
import nl.competitie.badminton.BadmintonCompetitie.repository.BadmintonCompetitionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Controller
public class BadmintonCompetitionUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    BadmintonCompetitionUserRepository badmintonCompetitionUserRepository;

    @GetMapping("/user/new")
    protected String showNewUserForm(Model model) {
        model.addAttribute("user", new BadmintonCompetitionUser());
        return "userForm";
    }

    @PostMapping("/user/new")
    protected String saveOrUpdateUser(@ModelAttribute("user")BadmintonCompetitionUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "userForm";
        } else {
            // Encode given plain text password, before saving in DB
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            badmintonCompetitionUserRepository.save(user);
            return "redirect:/";
        }
    }
}
