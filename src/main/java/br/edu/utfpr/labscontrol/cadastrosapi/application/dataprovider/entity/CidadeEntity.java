package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "cidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited(targetAuditMode = NOT_AUDITED)
public class CidadeEntity {
    @Id
    @Column(unique = true)
    private String id;
    private String nome;
    @ManyToOne
    private EstadoEntity estado;
}
