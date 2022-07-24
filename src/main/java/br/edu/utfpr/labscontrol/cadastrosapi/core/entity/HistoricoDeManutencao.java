package br.edu.utfpr.labscontrol.cadastrosapi.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HistoricoDeManutencao {
    private String motivoDoDefeito;
    private LocalDate dataDoDefeito;
    private LocalDate dataDeEnvio;
    private LocalDate dataDeRetorno;
    private String manutencaoRealizada;
    private BigDecimal valorDaManuntecao;
}
