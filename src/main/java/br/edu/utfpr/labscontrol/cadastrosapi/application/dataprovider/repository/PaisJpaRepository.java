package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PaisJpaRepository extends JpaRepository<PaisEntity, Integer> {
}
