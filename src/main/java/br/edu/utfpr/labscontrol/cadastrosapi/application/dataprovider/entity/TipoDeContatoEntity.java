package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.AuditableEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.enums.TipoDeContatoEnum;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipos_de_contatos")
@NoArgsConstructor
@Data
@Audited
public class TipoDeContatoEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ContatosEntity contatos;
    @Enumerated(EnumType.STRING)
    private TipoDeContatoEnum tipoDeContato;
    private String ddd;
    private String numero;
    private String enderecoEmail;
    private String urlSite;
    private String texto;

    public static TipoDeContatoEntity of(TipoDeContato tipoDeContato) {
        if (tipoDeContato instanceof Email email) {
            return TipoDeContatoEntity.email(email.getEndereco());
        } else if (tipoDeContato instanceof Celular celular) {
            return TipoDeContatoEntity.celular(celular.getDdd(), celular.getNumero());
        } else if (tipoDeContato instanceof Telefone telefone) {
            return TipoDeContatoEntity.telefone(telefone.getDdd(), telefone.getNumero());
        } else if (tipoDeContato instanceof Site site) {
            return TipoDeContatoEntity.site(site.getUrl());
        } else {
            var outro = (OutroContato) tipoDeContato;
            return TipoDeContatoEntity.outro(outro.getTexto());
        }
    }

    public static TipoDeContatoEntity outro(String texto) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.OUTRO;
        tipo.texto = texto;
        return tipo;
    }

    public static TipoDeContatoEntity site(String url) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.SITE;
        tipo.urlSite = url;
        return tipo;
    }

    public static TipoDeContatoEntity email(String endereco) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.EMAIL;
        tipo.enderecoEmail = endereco;
        return tipo;
    }

    public static TipoDeContatoEntity celular(String ddd, String numero) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.CELULAR;
        tipo.ddd = ddd;
        tipo.numero = numero;
        return tipo;
    }

    public static TipoDeContatoEntity telefone(String ddd, String numero) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.TELEFONE;
        tipo.ddd = ddd;
        tipo.numero = numero;
        return tipo;
    }
}
