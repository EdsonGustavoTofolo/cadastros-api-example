package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.exception.BusinessException;
import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.FornecedorMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.UpdateEntityCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class UpdateFornecedorCommand implements UpdateEntityCommand<FornecedorDto, Integer> {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Override
    public void execute(Integer id, FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = fornecedorRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new BusinessException("Fornecedor não encontrado com ID: " + id, HttpStatus.NOT_FOUND));

        fornecedor.setRazaoSocial(fornecedorDto.getRazaoSocial());
        fornecedor.setNomeFantasia(fornecedorDto.getNomeFantasia());

        fornecedorRepository.atualizar(id, fornecedor);
    }
}
