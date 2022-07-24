package br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto.ContatosDto;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.TipoDeContato;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {CnpjMapper.class, CepMapper.class})
public interface FornecedorMapper {

    @Mapping(target = "endereco.logradouro", source = "logradouro")
    @Mapping(target = "endereco.numero", source = "numero")
    @Mapping(target = "endereco.bairro", source = "bairro")
    @Mapping(target = "endereco.complemento", source = "complemento")
    @Mapping(target = "endereco.pontoDeReferencia", source = "pontoDeReferencia")
    @Mapping(target = "endereco.cep", source = "cep")
    @Mapping(target = "endereco.cidade.id", source = "cidadeId")
    @Mapping(target = "contatos.tipos", source = "contatos", qualifiedByName = "contatosBaseModelToTipoDeContatos")
    @Mapping(target = "contatos.observacao", source = "contatosObservacao")
    Fornecedor toModel(FornecedorDto fornecedorDto);

    @Mapping(target = "cnpj", expression = "java(fornecedor.getCnpj().toString())")
    @Mapping(target = "logradouro", source = "endereco.logradouro")
    @Mapping(target = "numero", source = "endereco.numero")
    @Mapping(target = "bairro", source = "endereco.bairro")
    @Mapping(target = "complemento", source = "endereco.complemento")
    @Mapping(target = "pontoDeReferencia", source = "endereco.pontoDeReferencia")
    @Mapping(target = "cep", expression = "java(fornecedor.getEndereco().getCep().toString())")
    @Mapping(target = "cidadeId", source = "endereco.cidade.id")
    @Mapping(target = "contatosObservacao", source = "contatos.observacao")
    @Mapping(target = "contatos", source = "contatos.tipos", qualifiedByName = "tipoDeContatosToContatosBaseModel")
    FornecedorDto toDto(Fornecedor fornecedor);

    @Named("tipoDeContatosToContatosBaseModel")
    static List<ContatosDto> tipoDeContatosToContatosBaseModel(List<TipoDeContato> tipoDeContatos) {
        return tipoDeContatos.stream().map(tipo -> new TipoDeContatoMapper().map(tipo)).toList();
    }

    @Named("contatosBaseModelToTipoDeContatos")
    static List<TipoDeContato> contatosBaseModelToTipoDeContatos(List<ContatosDto> contatos) {
        return contatos.stream().map(contato -> new TipoDeContatoMapper().map(contato)).toList();
    }
}
