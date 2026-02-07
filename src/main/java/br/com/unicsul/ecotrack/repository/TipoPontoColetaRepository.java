package br.com.unicsul.ecotrack.repository;

import br.com.unicsul.ecotrack.model.TipoPontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoPontoColetaRepository extends JpaRepository<TipoPontoColeta, Long> {
    Optional<TipoPontoColeta> findByNome(String nome);
}
