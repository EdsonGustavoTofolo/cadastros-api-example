package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paises")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaisEntity {
    @Id
    @Column(unique = true)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private String nome;
}
