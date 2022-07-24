package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.Produto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "equipamentos")
public class EquipamentoEntity extends Produto {
    private String patrimonio;
    private String partNumber;
    private LocalDate dataDeAquisicao;
//    private List<HistoricoDeManutencao> manutencoes;
}
