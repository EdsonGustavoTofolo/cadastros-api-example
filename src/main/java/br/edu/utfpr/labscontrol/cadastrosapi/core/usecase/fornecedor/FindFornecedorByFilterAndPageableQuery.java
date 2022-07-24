package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.FindEntityByFilterAndPageableQuery;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Map;

public class FindFornecedorByFilterAndPageableQuery implements FindEntityByFilterAndPageableQuery<FornecedorDto> {

    @Override
    public Page<FornecedorDto> execute(Map<String, String> filters, Pageable pageable) {
        return null;
    }
}
