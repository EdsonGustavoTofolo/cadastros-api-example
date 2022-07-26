package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.AuditableEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditOverride;
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
@AuditOverride(forClass = AuditableEntity.class)
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

    public void of(final Fornecedor fornecedor) {
        this.cnpj = fornecedor.getCnpj().toString();
        this.razaoSocial = fornecedor.getRazaoSocial();
        this.nomeFantasia = fornecedor.getNomeFantasia();
        this.endereco.of(fornecedor.getEndereco());
        this.contatos.of(fornecedor.getContatos());
    }
}
