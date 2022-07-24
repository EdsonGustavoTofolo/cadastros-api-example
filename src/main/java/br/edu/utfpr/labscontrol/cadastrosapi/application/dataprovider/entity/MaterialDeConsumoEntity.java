package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base.Produto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "materiais_de_consumo")
public class MaterialDeConsumoEntity extends Produto {
    private BigDecimal quantidadeAtual;
    private BigDecimal quantidadeMinima;
}
