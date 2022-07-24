package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Endereco;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.Cep;

import static br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder.CidadeBuilder.umaCidade;

public class EnderecoBuilder {
    private Endereco endereco;

    private EnderecoBuilder() {}

    public static EnderecoBuilder umEndereco() {
        var builder = new EnderecoBuilder();
        builder.endereco = Endereco.builder()
                .logradouro("Rua Marrecas")
                .bairro("Luther King")
                .numero("190")
                .complemento("edificio Santana")
                .pontoDeReferencia("prox ao bar Docas")
                .cep(new Cep("85605000"))
                .cidade(umaCidade().get())
                .build();
        return builder;
    }

    public Endereco get() {
        return this.endereco;
    }
}
