package br.edu.utfpr.labscontrol.cadastrosapi.core.usecase.fornecedor;

import br.edu.utfpr.labscontrol.cadastrosapi.core.dataprovider.FornecedorRepository;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Cidade;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.Fornecedor;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.Cep;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.dto.FornecedorDto;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.exception.EntityNotFoundException;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper.TipoDeContatoMapper;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.usecase.UpdateEntityCommand;
import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateFornecedorCommand implements UpdateEntityCommand<FornecedorDto, Integer> {

    private final FornecedorRepository fornecedorRepository;

    @Override
    public void execute(Integer id, FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = fornecedorRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Fornecedor não encontrado com ID: " + id));

        fornecedor.setCnpj(new Cnpj(fornecedorDto.getCnpj()));
        fornecedor.setRazaoSocial(fornecedorDto.getRazaoSocial());
        fornecedor.setNomeFantasia(fornecedorDto.getNomeFantasia());
        fornecedor.setObservacao(fornecedorDto.getObservacao());

        var endereco = fornecedor.getEndereco();
        endereco.setLogradouro(fornecedorDto.getLogradouro());
        endereco.setNumero(fornecedorDto.getNumero());
        endereco.setBairro(fornecedorDto.getBairro());
        endereco.setCep(new Cep(fornecedorDto.getCep()));
        endereco.setComplemento(fornecedorDto.getComplemento());
        endereco.setPontoDeReferencia(fornecedorDto.getPontoDeReferencia());
        endereco.setCidade(Cidade.builder().id(fornecedorDto.getCidadeId()).build());

        var tipoDeContatoMapper = new TipoDeContatoMapper();
        var contatos = fornecedor.getContatos();
        contatos.getTipos().clear();
        contatos.setTipos(fornecedorDto.getContatos().stream().map(tipoDeContatoMapper::map).toList());
        contatos.setObservacao(fornecedorDto.getObservacao());

        fornecedorRepository.atualizar(id, fornecedor);
    }
}
