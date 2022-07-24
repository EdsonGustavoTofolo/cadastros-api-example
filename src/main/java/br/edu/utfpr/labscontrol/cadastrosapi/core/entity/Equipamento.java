package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.base.Produto;

import java.time.LocalDate;
import java.util.List;

public class Equipamento extends Produto {
    private String patrimonio;
    private String partNumber;
    private LocalDate dataDeAquisicao;
    private List<HistoricoDeManutencao> manutencoes;
}
