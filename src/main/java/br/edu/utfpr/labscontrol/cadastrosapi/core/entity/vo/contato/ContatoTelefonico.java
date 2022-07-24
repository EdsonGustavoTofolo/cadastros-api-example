package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import lombok.Getter;

@Getter
public abstract class ContatoTelefonico extends TipoDeContato {
    protected String ddd;
    protected String numero;

    public ContatoTelefonico(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
}
