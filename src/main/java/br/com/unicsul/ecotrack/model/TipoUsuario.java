package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_usu")
    private Long id;

    @Column(name = "nm_tipo_usu", length = 20, nullable = false)
    private String nome;

    @Column(name = "ds_tipo_usu", length = 255, nullable = false)
    private String descricao;
}
