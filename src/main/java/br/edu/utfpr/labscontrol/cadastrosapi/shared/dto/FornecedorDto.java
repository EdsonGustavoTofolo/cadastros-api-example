package br.edu.utfpr.labscontrol.cadastrosapi.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class FornecedorDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "CNPJ: Campo obrigatório")
    @Size(message = "Informe o CNPJ sem formatação, com somente {max} digitos", min = 14, max = 14)
    private String cnpj;
    @NotEmpty(message = "Razão Social: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private String razaoSocial;
    @NotEmpty(message = "Nome Fantasia: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private String nomeFantasia;
    private String observacao;
    @NotEmpty(message = "Logradouro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private String logradouro;
    @NotEmpty(message = "Número: Campo obrigatório. Caso nao exista infomar 'SN'")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 10)
    private String numero;
    @NotEmpty(message = "Bairro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 60)
    private String bairro;
    @Size(message = "Complemento: Tamanho máximo de {max} caracteres", max = 100)
    private String complemento;
    @Size(message = "Ponto de referência: Tamanho máximo de {max} caracteres", max = 100)
    private String pontoDeReferencia;
    @NotEmpty(message = "CEP: Campo obrigatório")
    @Size(message = "CEP: Tamanho máximo de {max} caracteres", max = 8)
    private String cep;
    @NotEmpty(message = "Cidade: Campo obrigatório")
    private String cidadeId;
    @NotEmpty(message = "Contatos: Campo obrigatório")
    private List<@Valid ContatosDto> contatos;
    private String contatosObservacao;
}
