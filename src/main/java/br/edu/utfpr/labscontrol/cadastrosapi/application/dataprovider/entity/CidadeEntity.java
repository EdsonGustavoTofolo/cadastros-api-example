package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeEntity {
    @Id
    @Column(unique = true)
    private String id;
    private String nome;
    @ManyToOne
    private EstadoEntity estado;
}
