package br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;

import java.util.Optional;

public interface FornecedorRepository {
    Integer persistir(final Fornecedor fornecedor);
    void atualizar(final Integer id, final Fornecedor fornecedor);
    void excluir(final Fornecedor fornecedor);
    Optional<Fornecedor> buscarPorId(final Integer id);
    Optional<Fornecedor> buscarPorCnpj(final Cnpj cnpj);
}
