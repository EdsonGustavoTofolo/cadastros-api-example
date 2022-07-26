package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.mapper;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.FornecedorEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.TipoDeContatoEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.TipoDeContato;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorView;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.TipoDeContatoView;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.CepMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.CnpjMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.TipoDeContatoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {CnpjMapper.class, CepMapper.class})
public interface FornecedorEntityMapper {

    @Mapping(target = "contatos.tipos", source = "contatos.tipos", qualifiedByName = "tipoDeContatosEntityToTipoDeContatos")
    Fornecedor toModel(FornecedorEntity fornecedorEntity);

    @Mapping(target = "contatos.tipos", source = "contatos.tipos", qualifiedByName = "tipoDeContatosViewToTipoDeContatos")
    Fornecedor toModel(FornecedorView fornecedorView);

    @Mapping(target = "cnpj", expression = "java(fornecedor.getCnpj().toString())")
    @Mapping(target = "endereco.cep", expression = "java(endereco.getCep().toString())")
    @Mapping(target = "contatos.tipos", source = "contatos.tipos", qualifiedByName = "tipoDeContatosToTipoDeContatosEntity")
    FornecedorEntity toEntity(Fornecedor fornecedor);

    @Named("tipoDeContatosToTipoDeContatosEntity")
    static List<TipoDeContatoEntity> tipoDeContatosToTipoDeContatosEntity(List<TipoDeContato> tiposDeContatos) {
        var tipoDeContatoMapper = new TipoDeContatoMapper();
        return tiposDeContatos.stream().map(tipoDeContato ->
                tipoDeContatoMapper.map(tipoDeContato, TipoDeContatoEntity.class)).toList();
    }


    @Named("tipoDeContatosViewToTipoDeContatos")
    static List<TipoDeContato> tipoDeContatosViewToTipoDeContatos(List<TipoDeContatoView> tipoDeContatoViews) {
        var tipoDeContatoMapper = new TipoDeContatoMapper();
        return tipoDeContatoViews.stream().map(tipoDeContatoMapper::map).toList();
    }

    @Named("tipoDeContatosEntityToTipoDeContatos")
    static List<TipoDeContato> tipoDeContatosEntityToTipoDeContatos(List<TipoDeContatoEntity> tipoDeContatoEntities) {
        var tipoDeContatoMapper = new TipoDeContatoMapper();
        return tipoDeContatoEntities.stream().map(tipoDeContatoMapper::map).toList();
    }
}
