package br.com.unicsul.ecotrack.repository;

import br.com.unicsul.ecotrack.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    Optional<TipoUsuario> findByNome(String nome);
}
