package cz.cmgs.mgor.controller;

import cz.cmgs.mgor.repository.RegistredPlayerRepository;
import cz.cmgs.mgor.service.RegistredPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
public class RegistredPlayerController {

    @Autowired
    RegistredPlayerService registredPlayerService;

    @GetMapping("/players")
    public String showRegistredPlayersList(Model model, String plKeyword) {
        if (plKeyword != null && !plKeyword.isEmpty()) {
            model.addAttribute("players", registredPlayerService.findByKeyword(plKeyword));
        } else {
            model.addAttribute("players", registredPlayerService.getRegistredPlayers());
        }
        return "players";
    }
}
