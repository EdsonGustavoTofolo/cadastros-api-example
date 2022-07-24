package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {
    @EqualsAndHashCode.Include
    private Integer id;
    @EqualsAndHashCode.Include
    private Cnpj cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private Endereco endereco;
    private String observacao;
    private Contatos contatos;
}
