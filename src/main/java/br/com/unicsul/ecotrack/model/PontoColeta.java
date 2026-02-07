package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pontos_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poncol_pco")
    private Long id;

    @Column(name = "nm_poncol_pco", length = 100, nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endere_end", nullable = false)
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pco", nullable = false)
    private TipoPontoColeta tipo;

    // O campo id_usuari_usu não é obrigatorio para a visualização publica,
    // mas pode ser mapeado se necessário no futuro.
}
