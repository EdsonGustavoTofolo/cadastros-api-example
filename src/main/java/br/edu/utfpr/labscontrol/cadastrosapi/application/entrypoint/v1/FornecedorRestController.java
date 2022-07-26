package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.base.CrudControllerBase;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor.*;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/fornecedores", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Fornecedor", description = "Cadastro de Fornecedor")
public class FornecedorRestController extends CrudControllerBase<FornecedorDto, Integer> {

    private final FindFornecedorByCnpjQuery findFornecedorByCnpjQuery;

    public FornecedorRestController(CreateFornecedorCommand createEntityCommand,
                                    UpdateFornecedorCommand updateEntityCommand,
                                    DeleteFornecedorByIdCommand deleteEntityByIdCommand,
                                    FindFornecedorByIdQuery findEntityByIdQuery,
                                    FindFornecedorByFilterAndPageableQuery findEntityByFilterAndPageable,
                                    FindFornecedorByCnpjQuery findFornecedorByCnpjQuery) {
        super(createEntityCommand, updateEntityCommand, deleteEntityByIdCommand, findEntityByIdQuery, findEntityByFilterAndPageable);
        this.findFornecedorByCnpjQuery = findFornecedorByCnpjQuery;
    }

    @Operation(summary = "Cria um novo fornecedor",
            description = "Cria um fornecedor com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Criado com sucesso",
                    headers = @Header(name = LOCATION,
                            description = "Retorna a URL do Fornecedor criado",
                            schema = @Schema(type = "string", format = "link"))),
            @ApiResponse(responseCode = "400",
                    description = "Dados do fornecedor inválidos")
    })
    @Override
    public ResponseEntity<Void> create(FornecedorDto fornecedor) {
        return super.create(fornecedor);
    }

    @Operation(summary = "Atualiza um fornecedor",
            description = "Atualiza o fornecedor com o ID passado na URL e com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Dados do Fornecedor inválido"),
            @ApiResponse(responseCode = "404",
                    description = "Fornecedor não encontrado")
    })
    @Override
    public void patch(Integer id, FornecedorDto fornecedor) {
        super.patch(id, fornecedor);
    }

    @Operation(summary = "Exclui um fornecedor",
            description = "Exclui um fornecedor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Excluido com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Fornecedor não encontrado")
    })
    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Operation(summary = "Busca um fornecedor",
            description = "Busca um fornecedor pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fornecedor encontrado",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FornecedorDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Fornecedor não encontrado",
                    content = @Content())
    })
    @Override
    public ResponseEntity<FornecedorDto> getById(Integer id) {
        return super.getById(id);
    }

    @Operation(summary = "Busca de fornecedores",
            description = "Busca fornecedores paginados conforme filtros e paginação informados")
    @Override
    public Page<FornecedorDto> getAllByFilterAndPageable(@RequestBody FornecedorDto filtros, Pageable pageable) {
        return super.getAllByFilterAndPageable(filtros, pageable);
    }

    @Operation(summary = "Busca um fornecedor",
            description = "Busca um fornecedor pelo Cnpj informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fornecedor encontrado",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FornecedorDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Fornecedor não encontrado",
                    content = @Content())
    })
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<FornecedorDto> buscarFornecedorPorCnpj(@PathVariable Cnpj cnpj) {
        var fornecedor = this.findFornecedorByCnpjQuery.execute(cnpj);
        return ResponseEntity.ok(fornecedor);
    }
}
