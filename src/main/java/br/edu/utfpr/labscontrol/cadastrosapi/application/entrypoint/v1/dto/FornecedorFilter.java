package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class FornecedorFilter {
    String cnpj;
    String nomeFantasia;
    String razaoSocial;
}
