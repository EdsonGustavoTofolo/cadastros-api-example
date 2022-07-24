package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.base.Produto;

import java.math.BigDecimal;

public class MaterialDeConsumo extends Produto {
    private BigDecimal quantidadeAtual;
    private BigDecimal quantidadeMinima;
}
