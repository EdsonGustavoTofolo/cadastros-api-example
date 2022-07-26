package br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.TipoDeContatoBase;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.exception.BusinessException;

import java.lang.reflect.Method;

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

    public <R> R map(TipoDeContato tipoDeContato, Class<R> resultClass) throws BusinessException {
        try {
            if (tipoDeContato instanceof Email email) {
                Method emailMethod = resultClass.getMethod("email", String.class);
                return (R) emailMethod.invoke(null, email.getEndereco());
            } else if (tipoDeContato instanceof Celular celular) {
                Method emailMethod = resultClass.getMethod("celular", String.class, String.class);
                return (R) emailMethod.invoke(null, celular.getDdd(), celular.getNumero());
            } else if (tipoDeContato instanceof Telefone telefone) {
                Method emailMethod = resultClass.getMethod("telefone", String.class, String.class);
                return (R) emailMethod.invoke(null, telefone.getDdd(), telefone.getNumero());
            } else if (tipoDeContato instanceof Site site) {
                Method emailMethod = resultClass.getMethod("site", String.class);
                return (R) emailMethod.invoke(null, site.getUrl());
            } else {
                var outro = (OutroContato) tipoDeContato;
                Method emailMethod = resultClass.getMethod("outro", String.class);
                return (R) emailMethod.invoke(null, outro.getTexto());
            }
        } catch (Exception e) {
            throw new BusinessException("Falha ao converter TipoDeContato para " + resultClass.getName());
        }
    }
}
