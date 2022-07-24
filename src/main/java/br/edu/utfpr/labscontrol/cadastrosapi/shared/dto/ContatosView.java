package br.edu.utfpr.labscontrol.cadastrosapi.shared.dto;

import java.util.List;

public interface ContatosView {
    Integer getId();
    List<TipoDeContatoView> getTipos();
    String observacao();
}
