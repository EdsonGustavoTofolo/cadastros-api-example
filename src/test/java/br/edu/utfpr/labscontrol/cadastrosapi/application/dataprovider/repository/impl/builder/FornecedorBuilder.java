package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Contatos;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Endereco;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;

public class FornecedorBuilder {

    private Fornecedor fornecedor;

    private FornecedorBuilder() {}

    public static FornecedorBuilder umFornecedor() {
        var builder = new FornecedorBuilder();
        builder.fornecedor = Fornecedor.builder()
                .cnpj(new Cnpj("70511054000105"))
                .razaoSocial("Fornecedor Ltda")
                .nomeFantasia("Fornecedor & Cia")
                .observacao("Falar sempre com Jose")
                .build();
        return builder;
    }

    public FornecedorBuilder comEndereco(Endereco endereco) {
        this.fornecedor.setEndereco(endereco);
        return this;
    }

    public FornecedorBuilder comContatos(Contatos contatos) {
        this.fornecedor.setContatos(contatos);
        return this;
    }

    public Fornecedor get() {
        return this.fornecedor;
    }
}
