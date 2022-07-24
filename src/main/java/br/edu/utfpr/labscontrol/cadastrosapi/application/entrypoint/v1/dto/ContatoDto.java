package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.TipoDeContato;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Value
@Builder
@Jacksonized
public class ContatoDto {
    Integer id;
    Set<TipoDeContato> tipos;
    String observacao;

    public void adicionarTipo(TipoDeContato tipoDeContato) {
        this.tipos.add(tipoDeContato);
    }
}
