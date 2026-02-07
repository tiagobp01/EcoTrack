package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuari_usu")
    private Long id;

    @Column(name = "nm_usuari_usu", length = 100, nullable = false)
    private String nome;

    @Column(name = "ds_email_usu", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "ds_senha_usu", length = 255, nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usu", nullable = false)
    private TipoUsuario tipo;

    @Column(name = "data_criacao_usuari_usu")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}
