package br.com.unicsul.ecotrack.controller;

import br.com.unicsul.ecotrack.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/pontos-coleta")
    public String pontosColeta(Model model) {
        model.addAttribute("pontos", pontoColetaRepository.findAll());
        return "pontos-coleta";
    }
}
