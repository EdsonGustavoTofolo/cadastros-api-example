package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.FornecedorEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.base.HibernateRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorView;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface FornecedorJpaRepository
        extends RevisionRepository<FornecedorEntity, Integer, Integer>, JpaRepository<FornecedorEntity, Integer>, HibernateRepository<FornecedorEntity> {
    Optional<FornecedorView> findByCnpj(String cnpj);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"contatos.tipos"})
    @Override
    Optional<FornecedorEntity> findById(Integer integer);
}
