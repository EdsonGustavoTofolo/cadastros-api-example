package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.validador.fornecedor.cadastro;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.core.util.ValidadorCnpj;

public class ValidadorCnpjValido extends ValidadorCadastroFornecedor {

    @Override
    public void executar(Fornecedor fornecedor) {
        ValidadorCnpj.executar(fornecedor.getCnpj().toString());
    }


}
