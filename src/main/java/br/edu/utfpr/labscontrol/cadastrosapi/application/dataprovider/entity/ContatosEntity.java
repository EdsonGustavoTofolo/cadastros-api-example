package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.AuditableEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contatos")
@Data
@Audited
public class ContatosEntity extends AuditableEntity implements Serializable {
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
