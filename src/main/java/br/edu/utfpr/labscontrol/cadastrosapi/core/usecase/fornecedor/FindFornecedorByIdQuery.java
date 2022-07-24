package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.FindEntityByIdQuery;

public class FindFornecedorByIdQuery implements FindEntityByIdQuery<FornecedorDto, Integer> {

    @Override
    public FornecedorDto execute(Integer integer) {
        return null;
    }
}
