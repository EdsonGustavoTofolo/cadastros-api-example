package br.edu.utfpr.labscontrol.cadastrosapi.application.configuration;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor.*;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.validador.fornecedor.cadastro.ValidadorCadastroFornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.validador.fornecedor.cadastro.ValidadorCnpjValido;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FornecedorBeanConfiguration {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Bean
    public CreateFornecedorCommand createFornecedorCommand() {
        return new CreateFornecedorCommand(
                validadoresCadastrodeFornecedor(),
                this.fornecedorRepository,
                this.mapper
        );
    }

    @Bean
    public UpdateFornecedorCommand updateFornecedorCommand() {
        return new UpdateFornecedorCommand(this.fornecedorRepository, this.mapper);
    }

    @Bean
    public DeleteFornecedorByIdCommand deleteFornecedorByIdCommand() {
        return new DeleteFornecedorByIdCommand();
    }

    @Bean
    public FindFornecedorByIdQuery findFornecedorByIdQuery() {
        return new FindFornecedorByIdQuery();
    }

    @Bean
    public FindFornecedorByCnpjQuery findFornecedorByCnpjQuery() {
        return new FindFornecedorByCnpjQuery(this.fornecedorRepository, this.mapper);
    }

    @Bean
    public FindFornecedorByFilterAndPageableQuery findFornecedorByFilterAndPageable() {
        return new FindFornecedorByFilterAndPageableQuery();
    }

    private List<ValidadorCadastroFornecedor> validadoresCadastrodeFornecedor() {
        return List.of(new ValidadorCnpjValido());
    }
}
