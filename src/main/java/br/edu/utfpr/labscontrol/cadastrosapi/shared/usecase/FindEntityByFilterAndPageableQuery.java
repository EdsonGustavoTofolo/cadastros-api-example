package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Map;

@FunctionalInterface
public interface FindEntityByFilterAndPageableQuery<T> {
    Page<T> execute(Map<String, String> filters, Pageable pageable);
}
