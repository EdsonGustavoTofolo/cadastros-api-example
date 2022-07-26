package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.FindEntityByFilterAndPageableQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Map;

@RequiredArgsConstructor
public class FindFornecedorByFilterAndPageableQuery implements FindEntityByFilterAndPageableQuery<FornecedorDto> {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Override
    public Page<FornecedorDto> execute(Map<String, String> filters, Pageable pageable) {
        return null;
    }
}
