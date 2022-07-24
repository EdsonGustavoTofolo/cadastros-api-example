package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.exception.BusinessException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Cep {
    private final String numero;

    public Cep(String numero) {
        if (numero == null || !numero.matches("\\d{8}")) {
            throw new BusinessException("Número do CEP inválido");
        }
        this.numero = numero;
    }

    @Override
    public String toString() {
        return this.numero;
    }
}
