package br.edu.utfpr.labscontrol.cadastrosapi.shared.dto;

public interface EnderecoView {
    Integer getId();
    String getLogradouro();
    String getBairro();
    String getNumero();
    String getComplemento();
    String getPontoDeReferencia();
    String getCep();
    CidadeView getCidade();
}
