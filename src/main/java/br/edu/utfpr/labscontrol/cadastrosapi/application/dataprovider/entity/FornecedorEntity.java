package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fornecedores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Audited
public class FornecedorEntity extends AuditableEntity implements Serializable {
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
}
