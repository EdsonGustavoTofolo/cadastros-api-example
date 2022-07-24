package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class OutroContato extends TipoDeContato {
    private String texto;

    public OutroContato(String texto) {
        this.texto = texto;
    }
}
