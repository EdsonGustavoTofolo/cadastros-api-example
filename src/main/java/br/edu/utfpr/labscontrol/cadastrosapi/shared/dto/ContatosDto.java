package br.edu.utfpr.labscontrol.cadastrosapi.shared.dto;

import br.edu.utfpr.labscontrol.cadastrosapi.application.annotations.Conditional;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.enums.TipoDeContatoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
@Jacksonized
@Conditional(selected = "tipoDeContato", values = {"TELEFONE", "CELULAR"}, required = {"ddd", "numero"}, message = "Informe o DDD e o Numero do contato telefonico")
@Conditional(selected = "tipoDeContato", values = "EMAIL", required = "enderecoEmail", message = "Informe o endereco de e-mail")
@Conditional(selected = "tipoDeContato", values = "SITE", required = "urlSite", message = "Informe uma URL valida")
@Conditional(selected = "tipoDeContato", values = "OUTRO", required = "texto", message = "Informe um texto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContatosDto implements TipoDeContatoBase {
    @NotNull(message = "Tipo de Contato deve ser informado")
    TipoDeContatoEnum tipoDeContato;
    @Size(message = "DDD: tamanho máximo de {max} digitos", min = 2, max = 2)
    String ddd;
    @Size(message = "Número: tamanho maximo de {max} digitos", max = 10)
    String numero;
    @Size(message = "Endereço de e-mail: tamanho máximo de {max} caracteres", max = 255)
    String enderecoEmail;
    @Size(message = "URL: tamanho máximo de {max} caracteres", max = 255)
    String urlSite;
    @Size(message = "Texto: tamanho máximo de {max} caracteres", max = 255)
    String texto;

    public static ContatosDto outro(String texto) {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto(texto)
                .build();
    }

    public static ContatosDto site(String url) {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite(url)
                .build();
    }

    public static ContatosDto telefone(String ddd, String numero) {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static ContatosDto celular(String ddd, String numero) {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static ContatosDto email(String endereco) {
        return ContatosDto.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail(endereco)
                .build();
    }
}
