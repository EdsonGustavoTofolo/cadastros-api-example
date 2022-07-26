package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.FornecedorEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.mapper.FornecedorEntityMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.CidadeJpaRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.FornecedorJpaRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto.FornecedorFilter;
import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.exception.BusinessException;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorRepositoryImpl implements FornecedorRepository {

    private final FornecedorJpaRepository fornecedorRepository;
    private final CidadeJpaRepository cidadeRepository;
    private final FornecedorEntityMapper fornecedorEntityMapper;

    @Transactional
    @Override
    public Integer persistir(final Fornecedor fornecedor) {
        if (fornecedor.getId() != null && fornecedor.getId() > 0) {
            throw new BusinessException("Para operacoes de atualizacao, utilize o metodo autalizar.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var cidadeEntity = this.cidadeRepository.getReferenceById(fornecedor.getEndereco().getCidade().getId());

        var fornecedorEntity = this.fornecedorEntityMapper.toEntity(fornecedor);
        fornecedorEntity.getEndereco().setCidade(cidadeEntity);

        var saved = this.fornecedorRepository.persist(fornecedorEntity);

        return saved.getId();
    }

    @Transactional
    @Override
    public void atualizar(final Integer id, final Fornecedor fornecedor) {
        if (fornecedor.getId() == null || fornecedor.getId() == 0) {
            throw new BusinessException("Para inclusao de Fornecedor utilize o metodo persistir!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        this.fornecedorRepository.findById(id)
                .ifPresent(fornecedorEntity -> {
                    fornecedorEntity.of(fornecedor);
                    this.fornecedorRepository.update(fornecedorEntity);
                });
    }

    @Override
    public Optional<Fornecedor> buscarPorCnpj(final Cnpj cnpj) {
        return this.fornecedorRepository.findByCnpj(cnpj.toString())
                .map(this.fornecedorEntityMapper::toModel);
    }

    @Override
    public Page<Fornecedor> buscaPaginada(final FornecedorFilter filtros, Pageable pageable) {
        var entityFiltros = FornecedorEntity.builder()
                .cnpj(filtros.getCnpj())
                .nomeFantasia(filtros.getNomeFantasia())
                .razaoSocial(filtros.getRazaoSocial())
                .build();

        var matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
        var example = Example.of(entityFiltros, matcher);

        return this.fornecedorRepository.findAll(example, pageable).map(this.fornecedorEntityMapper::toModel);
    }

    @Override
    public Optional<Fornecedor> buscarPorId(final Integer id) {
        return this.fornecedorRepository.findById(id)
                .map(this.fornecedorEntityMapper::toModel);
    }

    @Transactional
    @Override
    public void excluir(final Fornecedor fornecedor) {
        this.fornecedorRepository.deleteById(fornecedor.getId());
    }

}
