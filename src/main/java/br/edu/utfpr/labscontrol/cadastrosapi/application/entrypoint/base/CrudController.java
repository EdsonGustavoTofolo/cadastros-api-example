package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CrudController<T, F, ID> {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody T objectDto);

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void patch(@PathVariable ID id, @Valid @RequestBody T objectDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable ID id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<T> getById(@PathVariable ID id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<T> getAllByFilterAndPageable(F filtros, Pageable pageable);
}
