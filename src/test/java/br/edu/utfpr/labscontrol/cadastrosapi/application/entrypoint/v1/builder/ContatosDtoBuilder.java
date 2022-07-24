package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.builder;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.enums.TipoDeContatoEnum;
import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto.ContatosDto;

public class ContatosDtoBuilder {

    private ContatosDtoBuilder() {}

    public static ContatosDto umEmail() {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail("edson@mymail.com")
                .build();
    }

    public static ContatosDto umTelefone() {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd("46")
                .numero("35245865")
                .build();
    }

    public static ContatosDto umCelular() {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd("46")
                .numero("991023636")
                .build();
    }

    public static ContatosDto umSite() {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite("http://google.com.br")
                .build();
    }

    public static ContatosDto umOutroContato() {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto("0800 300 300")
                .build();
    }
}
