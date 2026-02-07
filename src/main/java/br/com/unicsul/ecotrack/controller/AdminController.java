package br.com.unicsul.ecotrack.controller;

import br.com.unicsul.ecotrack.model.*;
import br.com.unicsul.ecotrack.repository.*;
import br.com.unicsul.ecotrack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/adm")
public class AdminController {

    @Autowired
    private DescarteRepository descarteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PontoColetaService pontoColetaService;

    @Autowired
    private TipoPontoColetaRepository tipoPontoColetaRepository;

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public String index() {
        return "redirect:/adm/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "adm/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsuarios", usuarioRepository.count());
        model.addAttribute("totalDescartes", descarteRepository.count());
        model.addAttribute("totalPontos", pontoColetaService.buscarTodos().size());
        return "adm/dashboard";
    }

    @GetMapping("/pontos")
    public String listarPontos(Model model) {
        model.addAttribute("pontos", pontoColetaService.buscarTodos());
        return "adm/list-pontos";
    }

    @GetMapping("/pontos/novo")
    public String novoPonto(Model model) {
        PontoColeta ponto = new PontoColeta();
        ponto.setEndereco(new Endereco());
        model.addAttribute("ponto", ponto);
        model.addAttribute("tipos", tipoPontoColetaRepository.findAll());
        return "adm/form-ponto";
    }

    @PostMapping("/pontos/novo")
    public String salvarPonto(PontoColeta ponto, RedirectAttributes attributes) {
        try {
            pontoColetaService.salvar(ponto);
            attributes.addFlashAttribute("success", "Ponto de coleta cadastrado com sucesso!");
            return "redirect:/adm/pontos";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao cadastrar: " + e.getMessage());
            attributes.addFlashAttribute("ponto", ponto);
            return "redirect:/adm/pontos/novo";
        }
    }

    @GetMapping("/pontos/editar/{id}")
    public String editarPonto(@PathVariable Long id, Model model) {
        Optional<PontoColeta> ponto = pontoColetaService.buscarPorId(id);
        if (ponto.isEmpty()) {
            return "redirect:/adm/pontos";
        }
        model.addAttribute("ponto", ponto.get());
        model.addAttribute("tipos", tipoPontoColetaRepository.findAll());
        return "adm/form-ponto";
    }

    @PostMapping("/pontos/editar/{id}")
    public String atualizarPonto(@PathVariable Long id, PontoColeta ponto, RedirectAttributes attributes) {
        try {
            ponto.setId(id);
            pontoColetaService.salvar(ponto);
            attributes.addFlashAttribute("success", "Ponto de coleta atualizado com sucesso!");
            return "redirect:/adm/pontos";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao atualizar: " + e.getMessage());
            return "redirect:/adm/pontos/editar/" + id;
        }
    }

    @PostMapping("/pontos/excluir/{id}")
    public String excluirPonto(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            pontoColetaService.excluir(id);
            attributes.addFlashAttribute("success", "Ponto de coleta excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/adm/pontos";
    }

    @GetMapping("/pontos/relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorio() {
        try {
            byte[] pdf = relatorioService.gerarRelatorioPontosPorEstado(pontoColetaService.buscarTodos());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio-pontos.pdf");
            return ResponseEntity.ok().headers(headers).body(pdf);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(@RequestParam(required = false) String tipo, Model model) {
        if (tipo != null && !tipo.isEmpty()) {
            model.addAttribute("usuarios", usuarioService.buscarPorTipo(tipo));
        } else {
            model.addAttribute("usuarios", usuarioService.buscarTodos());
        }
        model.addAttribute("tipoSelecionado", tipo);
        return "adm/list-usuarios";
    }

    @GetMapping("/usuarios/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipos", tipoUsuarioRepository.findAll());
        return "adm/form-usuario";
    }

    @PostMapping("/usuarios/novo")
    public String salvarUsuario(Usuario usuario, RedirectAttributes attributes) {
        try {
            usuarioService.salvar(usuario);
            attributes.addFlashAttribute("success", "Usuário cadastrado com sucesso!");
            return "redirect:/adm/usuarios";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/adm/usuarios/novo";
        }
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isEmpty()) {
            return "redirect:/adm/usuarios";
        }
        model.addAttribute("usuario", usuario.get());
        model.addAttribute("tipos", tipoUsuarioRepository.findAll());
        return "adm/form-usuario";
    }

    @PostMapping("/usuarios/editar/{id}")
    public String atualizarUsuario(@PathVariable Long id, Usuario usuario,
            RedirectAttributes attributes) {
        try {
            usuario.setId(id);
            usuarioService.salvar(usuario);
            attributes.addFlashAttribute("success", "Usuário atualizado com sucesso!");
            return "redirect:/adm/usuarios";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao atualizar: " + e.getMessage());
            return "redirect:/adm/usuarios/editar/" + id;
        }
    }

    @PostMapping("/usuarios/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            usuarioService.excluir(id);
            attributes.addFlashAttribute("success", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/adm/usuarios";
    }

    @GetMapping("/usuarios/relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorioUsuarios() {
        try {
            byte[] pdf = relatorioService.gerarRelatorioUsuarios(usuarioService.buscarTodos());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio-usuarios.pdf");
            return ResponseEntity.ok().headers(headers).body(pdf);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Autowired
    private SituacaoDescarteRepository situacaoDescarteRepository;

    @GetMapping("/descartes")
    public String descartes(Model model) {
        model.addAttribute("descartes", descarteRepository.findAll());
        model.addAttribute("situacoes", situacaoDescarteRepository.findAll());
        return "adm/descartes";
    }

    @PostMapping("/descartes/status/{id}")
    public String atualizarStatusDescarte(@PathVariable Long id, @RequestParam Long situacaoId,
            RedirectAttributes attributes) {
        try {
            Descarte descarte = descarteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Solicitação não encontrada."));
            SituacaoDescarte situacao = situacaoDescarteRepository.findById(situacaoId)
                    .orElseThrow(() -> new RuntimeException("Situação inválida."));

            descarte.setSituacao(situacao);
            descarteRepository.save(descarte);
            attributes.addFlashAttribute("success", "Status da solicitação atualizado!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao atualizar status: " + e.getMessage());
        }
        return "redirect:/adm/descartes";
    }

    @GetMapping("/descartes/relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorioDescartes() {
        try {
            byte[] pdf = relatorioService.gerarRelatorioDescartes(descarteRepository.findAll());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio-descartes.pdf");
            return ResponseEntity.ok().headers(headers).body(pdf);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
