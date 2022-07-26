package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.FindEntityByIdQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
public class FindFornecedorByIdQuery implements FindEntityByIdQuery<FornecedorDto, Integer> {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Override
    public FornecedorDto execute(Integer id) {
        return this.fornecedorRepository.buscarPorId(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com ID: " + id));
    }
}
