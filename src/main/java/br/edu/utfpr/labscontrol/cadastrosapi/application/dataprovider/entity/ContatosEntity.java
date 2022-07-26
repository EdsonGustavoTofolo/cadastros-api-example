package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.AuditableEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Contatos;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.TipoDeContatoMapper;
import lombok.Data;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "contatos")
@Data
@Audited
@AuditOverride(forClass = AuditableEntity.class)
public class ContatosEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "contatos", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TipoDeContatoEntity> tipos;
    private String observacao;

    public void of(final Contatos contatos) {
        var tipoDeContatoMapper = new TipoDeContatoMapper();
        contatos.getTipos().forEach(tipoDeContato -> {
            TipoDeContatoEntity tipoDeContatoEntity = tipoDeContatoMapper.map(tipoDeContato, TipoDeContatoEntity.class);
            tipoDeContatoEntity.setContatos(this);
            tipos.add(tipoDeContatoEntity);
        });
        this.observacao = contatos.getObservacao();
    }
}
