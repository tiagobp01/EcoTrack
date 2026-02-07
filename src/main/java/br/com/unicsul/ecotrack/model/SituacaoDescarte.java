package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "situacao_descarte")
public class SituacaoDescarte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situac_desc")
    private Long id;

    @Column(name = "nm_situac_desc", length = 20, nullable = false)
    private String nome;

    @Column(name = "ds_situac_desc", length = 255)
    private String descricao;
}
