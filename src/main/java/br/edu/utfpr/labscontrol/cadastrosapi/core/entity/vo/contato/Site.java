package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Site extends TipoDeContato {
    private String url;

    public Site(String url) {
        this.url = url;
    }
}
