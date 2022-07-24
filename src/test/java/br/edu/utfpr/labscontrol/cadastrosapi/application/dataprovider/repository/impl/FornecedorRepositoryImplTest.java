package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl;

import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.FornecedorEntity;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.CidadeJpaRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.FornecedorJpaRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.TipoDeContatoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder.ContatosBuilder.umContatos;
import static br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder.EnderecoBuilder.umEndereco;
import static br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.repository.impl.builder.FornecedorBuilder.umFornecedor;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@EnableJpaRepositories(basePackageClasses = {FornecedorJpaRepository.class, CidadeJpaRepository.class})
@TestPropertySource("classpath:application.properties")
class FornecedorRepositoryImplTest {

    @Autowired
    private FornecedorJpaRepository fornecedorJpaRepository;
    @Autowired
    private CidadeJpaRepository cidadeJpaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private FornecedorRepository fornecedorRepository;

    @BeforeEach
    public void setup() {
        this.fornecedorRepository = new FornecedorRepositoryImpl(
                fornecedorJpaRepository, cidadeJpaRepository, new TipoDeContatoMapper());
    }

    @Test
    @Disabled
    void deveAtualizarNomeDoFornecedorSemChamarExplicitamenteOsaveAposFindById() {
        // cenario
        var fornecedor = umFornecedor()
                .comContatos(umContatos().get())
                .comEndereco(umEndereco().get())
                .get();

        // execucao
        var id = this.fornecedorRepository.persistir(fornecedor);
        FornecedorEntity fe = this.fornecedorJpaRepository.findById(id).get();

        this.entityManager.flush();
        this.entityManager.detach(fe);
        this.entityManager.clear();

        fe = this.fornecedorJpaRepository.findById(id).get();

        fe.setNomeFantasia("Novo Nome");

        this.entityManager.flush();
        this.entityManager.detach(fe);
        this.entityManager.clear();

        fe = this.fornecedorJpaRepository.findById(id).get();

        Assertions.assertEquals("Novo Nome", fe.getNomeFantasia());

        FornecedorEntity feReference = this.fornecedorJpaRepository.getReferenceById(id);

        feReference.setNomeFantasia("Nome atraves da referencia");

        fe = this.fornecedorJpaRepository.findById(id).get();
        Assertions.assertEquals("Nome atraves da referencia", fe.getNomeFantasia());
    }

    @Test
    void deveSalvarUmFornecedorComSucesso() {
        // cenario
        var fornecedor = umFornecedor()
                .comContatos(umContatos().get())
                .comEndereco(umEndereco().get())
                .get();

        // execucao
        var id = this.fornecedorRepository.persistir(fornecedor);

        // verificacao
        assertNotNull(id);

        var fornecedorEntity = this.fornecedorJpaRepository.findById(id).orElse(null);
        assertNotNull(fornecedorEntity);
        assertEquals(id, fornecedorEntity.getId());

        var endereco = fornecedorEntity.getEndereco();
        assertNotNull(endereco);
        assertNotNull(endereco.getId());

        var contatos = fornecedorEntity.getContatos();
        assertNotNull(contatos);
        assertNotNull(contatos.getId());
        assertEquals(5, contatos.getTipos().size());
    }

    @Test
    void deveBuscarFornecedorPorCnpj() {
        // cenario
        var fornecedorSaved = umFornecedor()
                .comContatos(umContatos().get())
                .comEndereco(umEndereco().get())
                .get();
        var id = this.fornecedorRepository.persistir(fornecedorSaved);
        fornecedorSaved.setId(id);

        // execucao
        var fornecedorResponse = this.fornecedorRepository.buscarPorCnpj(fornecedorSaved.getCnpj()).orElse(null);

        // verificacao
        assertNotNull(fornecedorResponse);
        assertNotNull(fornecedorResponse.getId());
        assertEquals(fornecedorSaved.getId(), fornecedorResponse.getId());
        assertEquals(fornecedorSaved.getCnpj(), fornecedorResponse.getCnpj());
        assertEquals(fornecedorSaved.getRazaoSocial(), fornecedorResponse.getRazaoSocial());
        assertEquals(fornecedorSaved.getNomeFantasia(), fornecedorResponse.getNomeFantasia());
        assertEquals(fornecedorSaved.getObservacao(), fornecedorResponse.getObservacao());

        assertNotNull(fornecedorResponse.getContatos());
        assertNotNull(fornecedorResponse.getContatos().getId());
        assertEquals(fornecedorSaved.getContatos().getTipos().size(), fornecedorResponse.getContatos().getTipos().size());
        assertTrue(fornecedorResponse.getContatos().getTipos().get(0) instanceof Celular);
        assertTrue(fornecedorResponse.getContatos().getTipos().get(1) instanceof Telefone);
        assertTrue(fornecedorResponse.getContatos().getTipos().get(2) instanceof Email);
        assertTrue(fornecedorResponse.getContatos().getTipos().get(3) instanceof Site);
        assertTrue(fornecedorResponse.getContatos().getTipos().get(4) instanceof OutroContato);

        assertNotNull(fornecedorResponse.getEndereco());
        assertNotNull(fornecedorResponse.getEndereco().getId());
        assertEquals(fornecedorSaved.getEndereco().getLogradouro(), fornecedorResponse.getEndereco().getLogradouro());
        assertEquals(fornecedorSaved.getEndereco().getNumero(), fornecedorResponse.getEndereco().getNumero());
        assertEquals(fornecedorSaved.getEndereco().getBairro(), fornecedorResponse.getEndereco().getBairro());
        assertEquals(fornecedorSaved.getEndereco().getComplemento(), fornecedorResponse.getEndereco().getComplemento());
        assertEquals(fornecedorSaved.getEndereco().getPontoDeReferencia(), fornecedorResponse.getEndereco().getPontoDeReferencia());
        assertEquals(fornecedorSaved.getEndereco().getCep(), fornecedorResponse.getEndereco().getCep());

        assertNotNull(fornecedorSaved.getEndereco().getCidade());
        assertNotNull(fornecedorSaved.getEndereco().getCidade().getId());
        assertEquals(fornecedorSaved.getEndereco().getCidade().getId(), fornecedorResponse.getEndereco().getCidade().getId());
    }

    @Test
    @Disabled
    void deveBuscarFornecedorPorId_Disabled_UtilizadoApenasParaVerificarAsintaxeDoSqlGerado() {
        // cenario
        var fornecedor = umFornecedor()
                .comContatos(umContatos().get())
                .comEndereco(umEndereco().get())
                .get();

        // execucao
        var id = this.fornecedorRepository.persistir(fornecedor);

        // verificacao
        assertNotNull(id);

        var fornecedorEntity = this.fornecedorJpaRepository.findById(id).orElse(null);

        this.entityManager.flush();
        this.entityManager.detach(fornecedorEntity);
        this.entityManager.clear();

        var found = this.fornecedorJpaRepository.findById(id).orElse(null);

        assertNotNull(found);
        assertEquals(2, found.getContatos().getTipos().size());
    }
}