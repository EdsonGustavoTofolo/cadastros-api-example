package br.edu.utfpr.labscontrol.cadastrosapi.shared.mapper;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.vo.Cnpj;
import org.mapstruct.Mapper;

@Mapper
public interface CnpjMapper {
    Cnpj map(String numero);
}
