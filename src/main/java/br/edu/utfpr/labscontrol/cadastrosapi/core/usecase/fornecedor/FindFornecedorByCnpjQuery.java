package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.exception.EntityNotFoundException;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindFornecedorByCnpjQuery {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    public FornecedorDto execute(Cnpj cnpj) throws EntityNotFoundException {
        return this.fornecedorRepository.buscarPorCnpj(cnpj)
                .map(this.mapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado com o CNPJ informado!"));
    }
}
