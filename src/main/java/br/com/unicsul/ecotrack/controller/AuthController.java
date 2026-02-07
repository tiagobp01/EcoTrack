package br.com.unicsul.ecotrack.controller;

import br.com.unicsul.ecotrack.model.Usuario;
import br.com.unicsul.ecotrack.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String showSignupForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String signup(@ModelAttribute Usuario usuario, Model model, HttpServletRequest request) {
        String senhaPura = usuario.getSenha();
        try {
            usuarioService.salvar(usuario);
            // Login automático após cadastro
            request.login(usuario.getEmail(), senhaPura);
            return "redirect:/painel";
        } catch (RuntimeException | ServletException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);
            return "cadastro";
        }
    }
}
