package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.validador;

public interface Validador<T> {
    void executar(T t);
}
