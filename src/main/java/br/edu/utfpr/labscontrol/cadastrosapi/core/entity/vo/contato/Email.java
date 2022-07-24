package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Email extends TipoDeContato {
    private String endereco;

    public Email(String endereco) {
        this.endereco = endereco;
    }
}
