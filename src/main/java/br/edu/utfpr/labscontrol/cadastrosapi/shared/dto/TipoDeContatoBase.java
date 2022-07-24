package br.edu.utfpr.labscontrol.cadastrosapi.shared.dto;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.enums.TipoDeContatoEnum;

public interface TipoDeContatoBase {
    TipoDeContatoEnum getTipoDeContato();
    String getDdd();
    String getNumero();
    String getEnderecoEmail();
    String getUrlSite();
    String getTexto();
}
