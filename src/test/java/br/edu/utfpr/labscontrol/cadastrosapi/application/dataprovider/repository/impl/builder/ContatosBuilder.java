package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Contatos;

public class ContatosBuilder {
    private Contatos contatos;

    private ContatosBuilder() {}

    public static ContatosBuilder umContatos() {
        var builder = new ContatosBuilder();
        builder.contatos = Contatos.builder()
                .observacao("Sem obs")
                .build();
        builder.contatos
                .adicionarTipo(TipoDeContatoBuilder.celular("46", "91115522"))
                .adicionarTipo(TipoDeContatoBuilder.telefone("49", "35219090"))
                .adicionarTipo(TipoDeContatoBuilder.email("person@mailme.com"))
                .adicionarTipo(TipoDeContatoBuilder.site("http://google.com.br"))
                .adicionarTipo(TipoDeContatoBuilder.outro("0800 300 300"));
        return builder;
    }

    public Contatos get() {
        return this.contatos;
    }
}
