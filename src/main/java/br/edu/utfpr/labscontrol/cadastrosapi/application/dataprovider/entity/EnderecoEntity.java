package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false, length = 10)
    private String numero;
    @Column(nullable = false, length = 60)
    private String bairro;
    @Column(length = 100)
    private String complemento;
    @Column(length = 100)
    private String pontoDeReferencia;
    @Column(nullable = false, length = 8)
    private String cep;
    @OneToOne
    private CidadeEntity cidade;
}
