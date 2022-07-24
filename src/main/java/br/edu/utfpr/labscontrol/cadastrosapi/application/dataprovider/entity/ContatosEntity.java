package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contatos")
@Data
public class ContatosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "contatos", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TipoDeContatoEntity> tipos;
    private String observacao;

    public void adicionarTipo(TipoDeContatoEntity tipo) {
        tipo.setContatos(this);
        if (Objects.isNull(this.tipos)) {
            this.tipos = new ArrayList<>();
        }
        this.tipos.add(tipo);
    }
}
