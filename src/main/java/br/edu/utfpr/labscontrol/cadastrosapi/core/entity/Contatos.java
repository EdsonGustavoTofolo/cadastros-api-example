package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.TipoDeContato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contatos {
    private Integer id;
    private List<TipoDeContato> tipos;
    private String observacao;

    public Contatos adicionarTipo(final TipoDeContato tipoDeContato) {
        if (tipos == null) {
            tipos = new ArrayList<>();
        }
        tipos.add(tipoDeContato);
        return this;
    }
}
