package br.edu.utfpr.labscontrol.cadastrosapi.shared.dto;

public interface FornecedorView {
    Integer getId();
    String getCnpj();
    String getRazaoSocial();
    String getNomeFantasia();
    String getObservacao();
    EnderecoView getEndereco();
    ContatosView getContatos();
}
