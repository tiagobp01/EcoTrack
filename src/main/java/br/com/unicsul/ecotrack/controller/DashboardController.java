package br.com.unicsul.ecotrack.controller;

import br.com.unicsul.ecotrack.model.Usuario;
import br.com.unicsul.ecotrack.repository.DescarteRepository;
import br.com.unicsul.ecotrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DescarteRepository descarteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/painel")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername()).orElse(null);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("descartes", descarteRepository.findByUsuario(usuario));
        }
        return "dashboard";
    }
}
