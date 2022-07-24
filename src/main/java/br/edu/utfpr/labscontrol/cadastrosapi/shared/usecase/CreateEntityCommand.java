package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

@FunctionalInterface
public interface CreateEntityCommand<T, ID> {
    ID execute(T object);
}
