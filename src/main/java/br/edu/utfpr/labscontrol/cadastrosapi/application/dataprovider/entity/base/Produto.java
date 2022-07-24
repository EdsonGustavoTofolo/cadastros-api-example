package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.FornecedorEntity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Produto {
    @Id
    private String id;
    protected String nome;
    protected String descricao;
    protected String foto;
//    protected Categoria categoria;
//    protected Modelo modelo;
//    protected Marca marca;
    @ManyToOne
    protected FornecedorEntity fornecedor;
}
