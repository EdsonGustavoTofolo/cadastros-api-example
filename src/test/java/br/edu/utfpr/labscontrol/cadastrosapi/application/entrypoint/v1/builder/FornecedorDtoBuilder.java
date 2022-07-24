package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;

import java.util.List;

import static br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.builder.ContatosDtoBuilder.*;

public class FornecedorDtoBuilder {

    private FornecedorDto fornecedor;

    private FornecedorDtoBuilder() {}

    public static FornecedorDtoBuilder umFornecedor() {
        FornecedorDto fornecedorDto = FornecedorDto.builder()
                .cnpj("70511054000105")
                .razaoSocial("Fornecedor Ltda")
                .nomeFantasia("Fornecedor & Cia")
                .observacao("falar sempre com Jose")
                .logradouro("Rua Bolivia")
                .bairro("Luther King")
                .numero("888")
                .cep("85605410")
                .cidadeId("4108403")
                .complemento("casa da frente")
                .pontoDeReferencia("proximo ao CEMA")
                .contatos(List.of(umEmail(), umCelular(), umTelefone(), umSite(), umOutroContato()))
                .contatosObservacao("conversar com representante")
                .build();

        var builder = new FornecedorDtoBuilder();
        builder.fornecedor = fornecedorDto;
        return builder;
    }

    public FornecedorDtoBuilder semCnpj() {
        this.fornecedor.setCnpj("");
        return this;
    }

    public FornecedorDto get() {
        return fornecedor;
    }
}
