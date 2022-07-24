package br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto.ContatosDto;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.TipoDeContatoBase;
import org.springframework.stereotype.Component;

@Component
public class TipoDeContatoMapper {

    public TipoDeContato map(TipoDeContatoBase contato) {
        return switch (contato.getTipoDeContato()) {
            case EMAIL : yield new Email(contato.getEnderecoEmail());
            case TELEFONE : yield new Telefone(contato.getDdd(), contato.getNumero());
            case CELULAR : yield new Celular(contato.getDdd(), contato.getNumero());
            case SITE : yield new Site(contato.getUrlSite());
            case OUTRO : yield new OutroContato(contato.getTexto());
        };
    }

    public ContatosDto map(TipoDeContato tipoDeContato) {
        if (tipoDeContato instanceof Email email) {
            return ContatosDto.email(email.getEndereco());
        } else if (tipoDeContato instanceof Celular celular) {
            return ContatosDto.celular(celular.getDdd(), celular.getNumero());
        } else if (tipoDeContato instanceof Telefone telefone) {
            return ContatosDto.telefone(telefone.getDdd(), telefone.getNumero());
        } else if (tipoDeContato instanceof Site site) {
            return ContatosDto.site(site.getUrl());
        } else {
            var outro = (OutroContato) tipoDeContato;
            return ContatosDto.outroContato(outro.getTexto());
        }
    }
}
