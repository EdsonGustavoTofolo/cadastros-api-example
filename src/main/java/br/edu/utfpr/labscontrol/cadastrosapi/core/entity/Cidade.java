package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {
    @EqualsAndHashCode.Include
    private String id;
    private String nome;
    private Estado estado;
}
