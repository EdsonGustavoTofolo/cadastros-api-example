package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.Cep;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {
    @EqualsAndHashCode.Include
    private Integer id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String pontoDeReferencia;
    private Cep cep;
    private Cidade cidade;
}
