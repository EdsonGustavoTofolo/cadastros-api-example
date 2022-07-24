package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.v1;

import br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.base.CrudController;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import org.springframework.http.ResponseEntity;

public interface FornecedorController extends CrudController<FornecedorDto, Integer> {


    @Override
    ResponseEntity<Void> create(FornecedorDto fornecedor);


    @Override
    void patch(Integer integer, FornecedorDto fornecedor);


    @Override
    void deleteById(Integer id);


    @Override
    ResponseEntity<FornecedorDto> getById(Integer id);
}
