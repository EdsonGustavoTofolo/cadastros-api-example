package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "paises")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited(targetAuditMode = NOT_AUDITED)
public class PaisEntity {
    @Id
    @Column(unique = true)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private String nome;
}
