package br.com.unicsul.ecotrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endere_end")
    private Long id;

    @Column(name = "cd_numcep_end", length = 8, nullable = false)
    private String cep;

    @Column(name = "nm_lograd_end", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "ds_bairro_end", length = 255, nullable = false)
    private String bairro;

    @Column(name = "ds_cidade_end", length = 100, nullable = false)
    private String cidade;

    @Column(name = "ds_estado_end", length = 2, nullable = false)
    private String estado;

    @Column(name = "ds_comple_end", length = 255)
    private String complemento;

    @Column(name = "ds_mapurl_end", length = 500)
    private String urlMapa;
}
