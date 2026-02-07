package br.com.unicsul.ecotrack.repository;

import br.com.unicsul.ecotrack.model.Descarte;
import br.com.unicsul.ecotrack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DescarteRepository extends JpaRepository<Descarte, Long> {
    List<Descarte> findByUsuario(Usuario usuario);
}
