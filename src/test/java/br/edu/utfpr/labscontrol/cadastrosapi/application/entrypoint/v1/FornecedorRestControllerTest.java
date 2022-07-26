package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1;

import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor.CreateFornecedorCommand;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor.FindFornecedorByCnpjQuery;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor.UpdateFornecedorCommand;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.exception.EntityNotFoundException;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.builder.FornecedorDtoBuilder.umFornecedor;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FornecedorRestControllerTest {

    private static final String URL_API_V1_FORNECEDORES = "/api/v1/fornecedores";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FindFornecedorByCnpjQuery findFornecedorByCnpjQuery;
    @MockBean
    private CreateFornecedorCommand createFornecedorCommand;
    @MockBean
    private UpdateFornecedorCommand updateFornecedorCommand;

    @Test
    void naoDeveCriarFonecedorSemCnpj() throws Exception {
        // cenario
        var fornecedor = umFornecedor().semCnpj().get();

        var fornecedorJson = this.mapper.writeValueAsString(fornecedor);

        // acao
        var perform = this.mvc.perform(
                post(URL_API_V1_FORNECEDORES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fornecedorJson));

        perform
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.mensagens").isArray())
                .andExpect(jsonPath("$.mensagens.length()", is(2)))
                .andDo(print()).andReturn();
    }

    @Test
    void naoDeveAtualizarFonecedorSemCnpj() throws Exception {
        // cenario
        var fornecedor = umFornecedor().semCnpj().get();

        var fornecedorJson = this.mapper.writeValueAsString(fornecedor);

        // acao
        var perform = this.mvc.perform(
                patch(URL_API_V1_FORNECEDORES + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fornecedorJson));

        perform
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.mensagens").isArray())
                .andExpect(jsonPath("$.mensagens.length()", is(2)))
                .andDo(print()).andReturn();
    }

    @Test
    void deveCriarFornecedorComSucesso() throws Exception {
        // cenario
        int id = 1;
        Mockito.when(createFornecedorCommand.execute(any(FornecedorDto.class))).thenReturn(id);

        var fornecedor = umFornecedor().get();
        var fornecedorJson = mapper.writeValueAsString(fornecedor);

        // acao/perform
        var perform = this.mvc.perform(
                post(URL_API_V1_FORNECEDORES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fornecedorJson)
        );

        // verificacao/expectations
                // validate response code and content type
        perform.andExpect(status().isCreated())

                // validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/fornecedores/".concat(String.valueOf(id))))

                .andDo(print())
                .andReturn();
    }

    @Test
    void deveAtualizarFornecedorComSucesso() throws Exception {
        // cenario
        var id = 1;
        var fornecedor = umFornecedor().get();
        var fornecedorJson = mapper.writeValueAsString(fornecedor);

        // execucao
        ResultActions perform = this.mvc.perform(
                patch(URL_API_V1_FORNECEDORES + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fornecedorJson));

        // verificacao
        perform.andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Mockito.verify(this.updateFornecedorCommand, Mockito.times(1)).execute(id, fornecedor);
    }

    @Test
    void deveLancarExecaoQuandoNaoLocalizarFornecedorPorCnpj() throws Exception {
        //cenario
        var cnpj = new Cnpj("70511054000105");

        var ex = new EntityNotFoundException("Fornecedor não encontrado com o CNPJ informado!");
        Mockito.doThrow(ex).when(findFornecedorByCnpjQuery).execute(cnpj);

        // perform/execucao
        var perform = this.mvc.perform(MockMvcRequestBuilders
                .get(URL_API_V1_FORNECEDORES + "/cnpj/" + cnpj));

        perform.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.mensagens[0]").value("Fornecedor não encontrado com o CNPJ informado!"))
                .andDo(print())
                .andReturn();
    }

    @Test
    void deveLocalizarFornecedorPorCnpj() throws Exception {
        //cenario
        var fornecedorDto = umFornecedor().get();

        Mockito.when(findFornecedorByCnpjQuery.execute(new Cnpj(fornecedorDto.getCnpj()))).thenReturn(fornecedorDto);

        // perform/execucao
        var perform = this.mvc.perform(MockMvcRequestBuilders
                .get(URL_API_V1_FORNECEDORES + "/cnpj/" + fornecedorDto.getCnpj()));

        // expectations/verificacao
        perform
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj").value(fornecedorDto.getCnpj()))
                .andExpect(jsonPath("$.nomeFantasia").value(fornecedorDto.getNomeFantasia()))
                .andExpect(jsonPath("$.razaoSocial").value(fornecedorDto.getRazaoSocial()))
                .andExpect(jsonPath("$.observacao").value(fornecedorDto.getObservacao()))
                .andExpect(jsonPath("$.logradouro").value(fornecedorDto.getLogradouro()))
                .andExpect(jsonPath("$.bairro").value(fornecedorDto.getBairro()))
                .andExpect(jsonPath("$.numero").value(fornecedorDto.getNumero()))
                .andExpect(jsonPath("$.complemento").value(fornecedorDto.getComplemento()))
                .andExpect(jsonPath("$.pontoDeReferencia").value(fornecedorDto.getPontoDeReferencia()))
                .andExpect(jsonPath("$.cep").value(fornecedorDto.getCep()))
                .andExpect(jsonPath("$.cidadeId").value(fornecedorDto.getCidadeId()))
                .andExpect(jsonPath("$.contatos").isArray())
                .andExpect(jsonPath("$.contatos", hasSize(5)))
                .andExpect(jsonPath("$.contatos[0].tipoDeContato").value(fornecedorDto.getContatos().get(0).getTipoDeContato().toString()))
                .andExpect(jsonPath("$.contatos[0].ddd").doesNotExist())
                .andExpect(jsonPath("$.contatos[0].numero").doesNotExist())
                .andExpect(jsonPath("$.contatos[0].urlSite").doesNotExist())
                .andExpect(jsonPath("$.contatos[0].enderecoEmail").value(fornecedorDto.getContatos().get(0).getEnderecoEmail()))
                .andExpect(jsonPath("$.contatos[0].texto").doesNotExist())
                .andExpect(jsonPath("$.contatos[1].tipoDeContato").value(fornecedorDto.getContatos().get(1).getTipoDeContato().toString()))
                .andExpect(jsonPath("$.contatos[1].ddd").value(fornecedorDto.getContatos().get(1).getDdd()))
                .andExpect(jsonPath("$.contatos[1].numero").value(fornecedorDto.getContatos().get(1).getNumero()))
                .andExpect(jsonPath("$.contatos[1].urlSite").doesNotExist())
                .andExpect(jsonPath("$.contatos[1].enderecoEmail").doesNotExist())
                .andExpect(jsonPath("$.contatos[1].texto").doesNotExist())
                .andExpect(jsonPath("$.contatos[2].tipoDeContato").value(fornecedorDto.getContatos().get(2).getTipoDeContato().toString()))
                .andExpect(jsonPath("$.contatos[2].ddd").value(fornecedorDto.getContatos().get(2).getDdd()))
                .andExpect(jsonPath("$.contatos[2].numero").value(fornecedorDto.getContatos().get(2).getNumero()))
                .andExpect(jsonPath("$.contatos[2].urlSite").doesNotExist())
                .andExpect(jsonPath("$.contatos[2].enderecoEmail").doesNotExist())
                .andExpect(jsonPath("$.contatos[2].texto").doesNotExist())
                .andExpect(jsonPath("$.contatos[3].tipoDeContato").value(fornecedorDto.getContatos().get(3).getTipoDeContato().toString()))
                .andExpect(jsonPath("$.contatos[3].ddd").doesNotExist())
                .andExpect(jsonPath("$.contatos[3].numero").doesNotExist())
                .andExpect(jsonPath("$.contatos[3].urlSite").value(fornecedorDto.getContatos().get(3).getUrlSite()))
                .andExpect(jsonPath("$.contatos[3].enderecoEmail").doesNotExist())
                .andExpect(jsonPath("$.contatos[3].texto").doesNotExist())
                .andExpect(jsonPath("$.contatos[4].tipoDeContato").value(fornecedorDto.getContatos().get(4).getTipoDeContato().toString()))
                .andExpect(jsonPath("$.contatos[4].ddd").doesNotExist())
                .andExpect(jsonPath("$.contatos[4].numero").doesNotExist())
                .andExpect(jsonPath("$.contatos[4].urlSite").doesNotExist())
                .andExpect(jsonPath("$.contatos[4].enderecoEmail").doesNotExist())
                .andExpect(jsonPath("$.contatos[4].texto").value(fornecedorDto.getContatos().get(4).getTexto()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @Disabled
    void deveLocalizarFornecedorPorCnpj_disabled() {
        // cenario
        var cnpj = new Cnpj("70511054000105");
        var nomeFantasia = "Empresa LTDA";
        var razaoSocial = "Chirubiru";

        var fornecedorDto = FornecedorDto.builder().build();

        var createFornecedor = Mockito.mock(CreateFornecedorCommand.class);
        var findFornecedorByCnpj = Mockito.mock(FindFornecedorByCnpjQuery.class);
        var updateFornecedor = Mockito.mock(UpdateFornecedorCommand.class);

        Mockito.when(findFornecedorByCnpj.execute(cnpj)).thenReturn(fornecedorDto);

//        var controller = new FornecedorRestControllerImpl(createFornecedor, findFornecedorByCnpj, updateFornecedor);

        /*
        // execucao
        var response = controller.buscarFornecedorPorCnpj(cnpj);

        // verificacao
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        var fornecedorResponse = response.getBody();
        Assertions.assertNotNull(fornecedorResponse);
        Assertions.assertEquals(cnpj, fornecedorResponse.getCnpj());
        Assertions.assertEquals(nomeFantasia, fornecedorResponse.getNomeFantasia());
        Assertions.assertEquals(razaoSocial, fornecedorResponse.getRazaoSocial());

         */
    }

}