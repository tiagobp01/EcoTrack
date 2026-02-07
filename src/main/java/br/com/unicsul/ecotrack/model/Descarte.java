package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "descartes")
public class Descarte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descar_des")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuari_usu", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_poncol_pco", nullable = false)
    private PontoColeta pontoColeta;

    @ManyToOne
    @JoinColumn(name = "id_catres_cre", nullable = false)
    private CategoriaResiduo categoria;

    @ManyToOne
    @JoinColumn(name = "id_situac_desc", nullable = false)
    private SituacaoDescarte situacao;

    @Column(name = "qt_descar_des", precision = 10, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "dt_descar_des")
    private LocalDateTime dataDescarte = LocalDateTime.now();
}
