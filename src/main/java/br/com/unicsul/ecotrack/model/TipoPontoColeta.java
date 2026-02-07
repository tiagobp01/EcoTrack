package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_ponto_coleta")
public class TipoPontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pco")
    private Long id;

    @Column(name = "nm_tipo_pco", length = 20, nullable = false)
    private String nome;

    @Column(name = "ds_tipo_pco", length = 255, nullable = false)
    private String descricao;
}
