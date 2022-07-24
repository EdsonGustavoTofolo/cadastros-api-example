package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Telefone extends ContatoTelefonico {

    public Telefone(String ddd, String numero) {
        super(ddd, numero);
    }
}
