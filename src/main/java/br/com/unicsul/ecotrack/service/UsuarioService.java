package br.com.unicsul.ecotrack.service;

import br.com.unicsul.ecotrack.model.Usuario;
import br.com.unicsul.ecotrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public java.util.List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public java.util.Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public java.util.List<Usuario> buscarPorTipo(String tipo) {
        return usuarioRepository.findByTipoNome(tipo);
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario.getId() == null) {
            // Novo usuário
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
                throw new RuntimeException("E-mail já cadastrado.");
            }
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        } else {
            // Edição: se a senha for enviada vazia, mantém a atual
            Usuario atual = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

            if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
                usuario.setSenha(atual.getSenha());
            } else {
                usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            }
            usuario.setDataCriacao(atual.getDataCriacao());
        }

        return usuarioRepository.save(usuario);
    }
}
