package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.exception.BusinessException;
import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class FindFornecedorByCnpjQuery {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    public FornecedorDto execute(Cnpj cnpj) throws BusinessException {
        return this.fornecedorRepository.buscarPorCnpj(cnpj)
                .map(this.mapper::toDto)
                .orElseThrow(() ->
                        new BusinessException("Fornecedor não encontrado com o CNPJ informado!", HttpStatus.NOT_FOUND));
    }
}
