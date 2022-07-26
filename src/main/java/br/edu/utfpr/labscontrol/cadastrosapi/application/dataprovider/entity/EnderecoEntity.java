package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.AuditableEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enderecos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditOverride(forClass = AuditableEntity.class)
public class EnderecoEntity extends AuditableEntity implements Serializable {
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

    public void of(final Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.complemento  = endereco.getComplemento();
        this.pontoDeReferencia = endereco.getPontoDeReferencia();
        this.cep = endereco.getCep().toString();
        this.cidade.setId(endereco.getCidade().getId());
    }
}
