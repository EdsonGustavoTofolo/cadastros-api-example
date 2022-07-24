package br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.Cep;
import org.mapstruct.Mapper;

@Mapper
public interface CepMapper {
    Cep map(String numero);
}
