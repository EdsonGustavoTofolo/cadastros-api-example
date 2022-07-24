package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Cidade;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Estado;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Pais;

public class CidadeBuilder {
    private Cidade cidade;

    private CidadeBuilder() {}

    public static CidadeBuilder umaCidade() {
        var builder = new CidadeBuilder();
        builder.cidade = Cidade.builder()
                .id("4108403")
                .nome("Francisco Beltrão")
                .estado(
                        Estado.builder()
                                .id(41)
                                .nome("Paraná")
                                .pais(Pais.brasil())
                                .build()
                )
                .build();
        return builder;
    }

    public Cidade get() {
        return this.cidade;
    }
}
