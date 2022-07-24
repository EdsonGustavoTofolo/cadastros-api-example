package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import lombok.*;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    private Integer id;
    private String nome;

    public static Pais brasil() {
        var pais = new Pais();
        pais.id = 76;
        pais.nome = "Brasil";
        return pais;
    }
}
