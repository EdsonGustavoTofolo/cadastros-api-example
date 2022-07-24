package br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase;

@FunctionalInterface
public interface DeleteEntityByIdCommand<ID> {
    void execute(ID id);
}
