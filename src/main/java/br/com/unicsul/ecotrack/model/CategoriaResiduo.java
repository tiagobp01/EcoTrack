package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias_residuo")
public class CategoriaResiduo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catres_cre")
    private Long id;

    @Column(name = "nm_catres_cre", length = 50, nullable = false, unique = true)
    private String nome;

    @Column(name = "ds_catres_cre", length = 255)
    private String descricao;
}
