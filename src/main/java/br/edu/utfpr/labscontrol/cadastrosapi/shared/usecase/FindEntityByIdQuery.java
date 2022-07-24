package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

@FunctionalInterface
public interface FindEntityByIdQuery<T, ID> {
    T execute(ID id);
}
