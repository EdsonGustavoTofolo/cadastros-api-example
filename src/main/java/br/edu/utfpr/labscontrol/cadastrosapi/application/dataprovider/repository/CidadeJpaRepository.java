package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, String> {
}
