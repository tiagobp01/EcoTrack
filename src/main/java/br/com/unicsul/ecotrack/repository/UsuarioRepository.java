package br.com.unicsul.ecotrack.repository;

import br.com.unicsul.ecotrack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    java.util.List<Usuario> findByTipoNome(String nome);
}
