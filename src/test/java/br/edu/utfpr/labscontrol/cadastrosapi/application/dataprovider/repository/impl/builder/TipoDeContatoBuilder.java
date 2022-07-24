package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;

public class TipoDeContatoBuilder {

    private TipoDeContatoBuilder() {}

    public static TipoDeContato email(String endereco) {
        return new Email(endereco);
    }

    public static TipoDeContato celular(String ddd, String numero) {
        return new Celular(ddd, numero);
    }

    public static TipoDeContato telefone(String ddd, String numero) {
        return new Telefone(ddd, numero);
    }

    public static TipoDeContato site(String url) {
        return new Site(url);
    }

    public static TipoDeContato outro(String texto) {
        return new OutroContato(texto);
    }
}
