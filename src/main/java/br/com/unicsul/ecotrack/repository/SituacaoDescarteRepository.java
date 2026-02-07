package br.com.unicsul.ecotrack.repository;

import br.com.unicsul.ecotrack.model.SituacaoDescarte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SituacaoDescarteRepository extends JpaRepository<SituacaoDescarte, Long> {
    Optional<SituacaoDescarte> findByNome(String nome);
}
