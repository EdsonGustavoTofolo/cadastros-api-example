package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.base;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;

public abstract class Produto {
    protected String nome;
    protected String descricao;
    protected String foto;
    protected Categoria categoria;
    protected Modelo modelo;
    protected Marca marca;
    protected Fornecedor fornecedor;
}
