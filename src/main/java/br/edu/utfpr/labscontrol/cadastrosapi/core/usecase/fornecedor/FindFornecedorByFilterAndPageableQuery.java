package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1.dto.FornecedorFilter;
import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.FindEntityByFilterAndPageableQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class FindFornecedorByFilterAndPageableQuery implements FindEntityByFilterAndPageableQuery<FornecedorDto, FornecedorFilter> {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Override
    public Page<FornecedorDto> execute(FornecedorFilter filters, Pageable pageable) {
        Page<Fornecedor> page = this.fornecedorRepository.buscaPaginada(filters, pageable);
        return page.map(this.mapper::toDto);
    }
}
