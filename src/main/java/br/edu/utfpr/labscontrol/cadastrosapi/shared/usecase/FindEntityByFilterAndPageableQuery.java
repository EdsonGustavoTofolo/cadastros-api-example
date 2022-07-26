package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface FindEntityByFilterAndPageableQuery<T, F> {
    Page<T> execute(F filters, Pageable pageable);
}
