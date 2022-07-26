package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.DeleteEntityByIdCommand;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
public class DeleteFornecedorByIdCommand implements DeleteEntityByIdCommand<Integer> {

    private final FornecedorRepository fornecedorRepository;

    @Override
    public void execute(Integer id) {
        Fornecedor fornecedor = fornecedorRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado com ID: " + id));
        this.fornecedorRepository.excluir(fornecedor);
    }
}
