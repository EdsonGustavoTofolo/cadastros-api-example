package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.validador.fornecedor.cadastro.ValidadorCadastroFornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.CreateEntityCommand;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateFornecedorCommand implements CreateEntityCommand<FornecedorDto, Integer> {

    private final List<ValidadorCadastroFornecedor> validacoes;
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Override
    public Integer execute(FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = this.mapper.toModel(fornecedorDto);

        this.validacoes.forEach(validador -> validador.executar(fornecedor));

        return this.fornecedorRepository.persistir(fornecedor);
    }
}
