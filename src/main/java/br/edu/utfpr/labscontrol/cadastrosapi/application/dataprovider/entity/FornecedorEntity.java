package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fornecedores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class FornecedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 14)
    private String cnpj;
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false)
    private String nomeFantasia;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private EnderecoEntity endereco;
    private String observacao;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private ContatosEntity contatos;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraUltimaAtualizacao;
}
