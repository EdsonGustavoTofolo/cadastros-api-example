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
        return new UpdateFornecedorCommand(this.fornecedorRepository);
    }

    @Bean
    public DeleteFornecedorByIdCommand deleteFornecedorByIdCommand() {
        return new DeleteFornecedorByIdCommand(this.fornecedorRepository);
    }

    @Bean
    public FindFornecedorByIdQuery findFornecedorByIdQuery() {
        return new FindFornecedorByIdQuery(this.fornecedorRepository, this.mapper);
    }

    @Bean
    public FindFornecedorByCnpjQuery findFornecedorByCnpjQuery() {
        return new FindFornecedorByCnpjQuery(this.fornecedorRepository, this.mapper);
    }

    @Bean
    public FindFornecedorByFilterAndPageableQuery findFornecedorByFilterAndPageable() {
        return new FindFornecedorByFilterAndPageableQuery(this.fornecedorRepository, this.mapper);
    }

    private List<ValidadorCadastroFornecedor> validadoresCadastrodeFornecedor() {
        return List.of(new ValidadorCnpjValido());
    }
}
