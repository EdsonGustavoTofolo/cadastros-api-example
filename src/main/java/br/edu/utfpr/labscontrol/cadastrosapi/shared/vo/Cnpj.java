package br.edu.utfpr.labscontrol.cadastrosapi.shared.vo;

import br.edu.utfpr.labscontrol.cadastrosapi.core.util.ValidadorCnpj;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Cnpj {
    private final String numero;

    public Cnpj(String numero) {
        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("Numero do CNPJ não informado!");
        }

        ValidadorCnpj.executar(numero);

        this.numero = numero;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.numero;
    }
}
