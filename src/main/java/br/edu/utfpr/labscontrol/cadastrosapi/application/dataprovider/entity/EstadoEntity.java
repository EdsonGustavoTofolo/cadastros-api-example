package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoEntity {
    @Id
    @Column(unique = true)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sigla;
    @ManyToOne
    private PaisEntity pais;
}
