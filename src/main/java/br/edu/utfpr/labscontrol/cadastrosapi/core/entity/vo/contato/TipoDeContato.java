package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.jsonAnnotations.CustomTipoDeContatoDeserializer;
import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.jsonAnnotations.CustomTipoDeContatoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomTipoDeContatoSerializer.class)
@JsonDeserialize(using = CustomTipoDeContatoDeserializer.class)
public abstract class TipoDeContato {
}
