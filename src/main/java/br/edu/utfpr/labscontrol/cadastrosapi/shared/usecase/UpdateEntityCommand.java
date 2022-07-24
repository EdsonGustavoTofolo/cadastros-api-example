package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

@FunctionalInterface
public interface UpdateEntityCommand<T, ID> {
    void execute(ID id, T object);
}
