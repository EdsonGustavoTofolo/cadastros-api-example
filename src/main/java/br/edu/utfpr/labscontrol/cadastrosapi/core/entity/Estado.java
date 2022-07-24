package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Estado {
    private Integer id;
    private String nome;
    private String sigla;
    private Pais pais;
}
