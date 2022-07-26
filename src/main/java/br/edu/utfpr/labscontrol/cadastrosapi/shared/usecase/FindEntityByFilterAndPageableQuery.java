package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface FindEntityByFilterAndPageableQuery<T> {
    Page<T> execute(T filters, Pageable pageable);
}
