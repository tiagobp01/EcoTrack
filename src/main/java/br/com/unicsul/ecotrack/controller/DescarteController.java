package br.com.unicsul.ecotrack.controller;

import br.com.unicsul.ecotrack.model.Descarte;
import br.com.unicsul.ecotrack.model.Usuario;
import br.com.unicsul.ecotrack.repository.CategoriaResiduoRepository;
import br.com.unicsul.ecotrack.repository.DescarteRepository;
import br.com.unicsul.ecotrack.repository.PontoColetaRepository;
import br.com.unicsul.ecotrack.repository.SituacaoDescarteRepository;
import br.com.unicsul.ecotrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DescarteController {

    @Autowired
    private DescarteRepository descarteRepository;

    @Autowired
    private CategoriaResiduoRepository categoriaResiduoRepository;

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @Autowired
    private SituacaoDescarteRepository situacaoDescarteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/solicitar-coleta")
    public String showForm(Model model) {
        model.addAttribute("descarte", new Descarte());
        model.addAttribute("categorias", categoriaResiduoRepository.findAll());
        model.addAttribute("pontos", pontoColetaRepository.findAll());
        return "solicitar-coleta";
    }

    @PostMapping("/solicitar-coleta")
    public String solicitar(@ModelAttribute Descarte descarte, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        descarte.setUsuario(usuario);
        descarte.setSituacao(situacaoDescarteRepository.findByNome("PENDENTE")
                .orElseThrow(() -> new RuntimeException("Situação PENDENTE não encontrada")));

        descarteRepository.save(descarte);
        return "redirect:/painel?success";
    }
}
