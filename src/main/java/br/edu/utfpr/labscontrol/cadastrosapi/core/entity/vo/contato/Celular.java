package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Celular extends ContatoTelefonico {

    public Celular(String ddd, String numero) {
        super(ddd, numero);
    }
}
