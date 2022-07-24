package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.ContatosEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.EnderecoEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.FornecedorEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.TipoDeContatoEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.CidadeJpaRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.FornecedorJpaRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.exception.BusinessException;
import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.*;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.Cep;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.TipoDeContato;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.TipoDeContatoView;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.TipoDeContatoMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorRepositoryImpl implements FornecedorRepository {

    private final FornecedorJpaRepository fornecedorRepository;
    private final CidadeJpaRepository cidadeRepository;
    private final TipoDeContatoMapper tipoDeContatoMapper;

    @Transactional
    @Override
    public Integer persistir(final Fornecedor fornecedor) {
        if (fornecedor.getId() != null && fornecedor.getId() > 0) {
            throw new BusinessException("Para operacoes de atualizacao, utilize o metodo autalizar.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Endereco endereco = fornecedor.getEndereco();

        var cidadeEntity = this.cidadeRepository
                .getReferenceById(endereco.getCidade().getId());

        var enderecoEntity = EnderecoEntity.builder()
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .pontoDeReferencia(endereco.getPontoDeReferencia())
                .cep(endereco.getCep().toString())
                .cidade(cidadeEntity)
                .build();

        List<TipoDeContatoEntity> tiposDeContatosEntity = new ArrayList<>();

        fornecedor.getContatos().getTipos()
                .stream().filter(Objects::nonNull)
                .forEach(tipoDeContato -> {
            TipoDeContatoEntity tipoDeContatoEntity = TipoDeContatoEntity.of(tipoDeContato);
            tiposDeContatosEntity.add(tipoDeContatoEntity);
        });

        ContatosEntity contatosEntity = null;
        if (!tiposDeContatosEntity.isEmpty()) {
            contatosEntity = new ContatosEntity();
            contatosEntity.setObservacao(fornecedor.getContatos().getObservacao());

            tiposDeContatosEntity.forEach(contatosEntity::adicionarTipo);
        }

        var fornecedorEntity = FornecedorEntity.builder()
                .cnpj(fornecedor.getCnpj().toString())
                .razaoSocial(fornecedor.getRazaoSocial())
                .nomeFantasia(fornecedor.getNomeFantasia())
                .endereco(enderecoEntity)
                .observacao(fornecedor.getObservacao())
                .contatos(contatosEntity)
                .dataHoraCriacao(LocalDateTime.now())
                .build();

        var saved = this.fornecedorRepository.persist(fornecedorEntity);

        return saved.getId();
    }

    @Transactional
    @Override
    public void atualizar(final Integer id, final Fornecedor fornecedor) {
        if (fornecedor.getId() == null || fornecedor.getId() == 0) {
            throw new BusinessException("Para inclusao de Fornecedor utilize o metodo persistir!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FornecedorEntity fornecedorEntity = this.fornecedorRepository.findById(id).get();
        fornecedorEntity.setNomeFantasia(fornecedor.getNomeFantasia());
        fornecedorEntity.setRazaoSocial(fornecedor.getRazaoSocial());
        fornecedorEntity.setDataHoraUltimaAtualizacao(LocalDateTime.now());
        this.fornecedorRepository.update(fornecedorEntity);
    }

    @Override
    public Optional<Fornecedor> buscarPorCnpj(Cnpj cnpj) {
        return this.fornecedorRepository.findByCnpj(cnpj.toString()).map(fornecedorView ->
                    Fornecedor.builder()
                            .id(fornecedorView.getId())
                            .cnpj(new Cnpj(fornecedorView.getCnpj()))
                            .razaoSocial(fornecedorView.getRazaoSocial())
                            .nomeFantasia(fornecedorView.getNomeFantasia())
                            .observacao(fornecedorView.getObservacao())
                            .endereco(Endereco.builder()
                                    .id(fornecedorView.getEndereco().getId())
                                    .logradouro(fornecedorView.getEndereco().getLogradouro())
                                    .bairro(fornecedorView.getEndereco().getBairro())
                                    .numero(fornecedorView.getEndereco().getNumero())
                                    .complemento(fornecedorView.getEndereco().getComplemento())
                                    .pontoDeReferencia(fornecedorView.getEndereco().getPontoDeReferencia())
                                    .cep(new Cep(fornecedorView.getEndereco().getCep()))
                                    .cidade(Cidade.builder()
                                            .id(fornecedorView.getEndereco().getCidade().getId())
                                            .nome(fornecedorView.getEndereco().getCidade().getNome())
                                            .estado(Estado.builder()
                                                    .id(fornecedorView.getEndereco().getCidade().getEstado().getId())
                                                    .nome(fornecedorView.getEndereco().getCidade().getEstado().getNome())
                                                    .pais(Pais.builder()
                                                            .id(fornecedorView.getEndereco().getCidade().getEstado().getPais().getId())
                                                            .nome(fornecedorView.getEndereco().getCidade().getEstado().getPais().getNome())
                                                            .build())
                                                    .build())
                                            .build())
                                    .build())
                            .contatos(
                                    Contatos.builder()
                                            .id(fornecedorView.getContatos().getId())
                                            .tipos(getTiposFrom(fornecedorView.getContatos().getTipos()))
                                            .observacao(fornecedorView.getObservacao())
                                            .build()
                            )
                            .build()
                );
    }

    @Override
    public Optional<Fornecedor> buscarPorId(Integer id) {
        return this.fornecedorRepository.findById(id).map(fornecedorEntity ->
                Fornecedor.builder()
                        .id(fornecedorEntity.getId())
                        .build());
    }

    private List<TipoDeContato> getTiposFrom(List<TipoDeContatoView> tipos) {
        return tipos.stream().map(this.tipoDeContatoMapper::map).toList();
    }
}
