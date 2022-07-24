package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.jsonAnnotations;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomTipoDeContatoSerializer extends JsonSerializer<TipoDeContato> {

    @Override
    public void serialize(TipoDeContato tipoDeContato, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(tipoDeContato.getClass().getName());

        jsonGenerator.writeStartObject();
        if (tipoDeContato instanceof Email email) {
            jsonGenerator.writeStringField("endereco", email.getEndereco());
        } else if (tipoDeContato instanceof ContatoTelefonico contatoTelefonico) {
            jsonGenerator.writeStringField("ddd", contatoTelefonico.getDdd());
            jsonGenerator.writeStringField("numero", contatoTelefonico.getNumero());
        } else if (tipoDeContato instanceof Site site) {
            jsonGenerator.writeStringField("url", site.getUrl());
        } else {
            OutroContato outroContato = (OutroContato) tipoDeContato;
            jsonGenerator.writeStringField("texto", outroContato.getTexto());
        }
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
