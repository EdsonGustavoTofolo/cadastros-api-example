package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.base;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
public abstract class CrudControllerBase<T, ID> implements CrudController<T, ID> {

    private final CreateEntityCommand<T, ID> createEntityCommand;
    private final UpdateEntityCommand<T, ID> updateEntityCommand;
    private final DeleteEntityByIdCommand<ID> deleteEntityByIdCommand;
    private final FindEntityByIdQuery<T, ID> findEntityByIdQuery;
    private final FindEntityByFilterAndPageableQuery<T> findEntityByFilterPageable;

    @Override
    public ResponseEntity<Void> create(T objectDto) {
        var id = this.createEntityCommand.execute(objectDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public void patch(ID id, T objectDto) {
        this.updateEntityCommand.execute(id, objectDto);
    }

    @Override
    public void deleteById(ID id) {
        this.deleteEntityByIdCommand.execute(id);
    }

    @Override
    public ResponseEntity<T> getById(ID id) {
        var response = this.findEntityByIdQuery.execute(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public Page<T> getAllByFilterAndPageable(T filtros, Pageable pageable) {
        return this.findEntityByFilterPageable.execute(filtros, pageable);
    }
}
