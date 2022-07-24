package br.edu.utfpr.labscontrol.cadastrosapi.core.entity.jsonAnnotations;

import br.edu.utfpr.labscontrol.cadastrosapi.core.entity.vo.contato.*;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CustomTipoDeContatoDeserializer extends JsonDeserializer<TipoDeContato> {

    private final List<String> classNames = List.of(
            Email.class.getName(),
            Celular.class.getName(),
            Site.class.getName(),
            Telefone.class.getName(),
            OutroContato.class.getName());

    @Override
    public TipoDeContato deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        var currentToken = jsonParser.getCurrentToken();

        java.lang.reflect.Constructor<?> rightConstructor = null;
        List<Object> parametersValues = new ArrayList<>();

        while (currentToken != JsonToken.END_OBJECT) {
            currentToken = jsonParser.nextToken();
            if (currentToken == JsonToken.FIELD_NAME) {
                var fieldName = jsonParser.getValueAsString();
                if (this.classNames.contains(fieldName)) {
                    try {
                        var tipoDeContatoClass = Class.forName(fieldName);
                        var constructors = tipoDeContatoClass.getConstructors();
                        for (java.lang.reflect.Constructor<?> constructor : constructors) {
                            if (constructor.getParameterCount() > 0) {
                                rightConstructor = constructor;
                                break;
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                parametersValues.add(jsonParser.getValueAsString());
            }
        }

        TipoDeContato tipoDeContato = null;

        if (rightConstructor != null) {
            try {
                tipoDeContato = (TipoDeContato) rightConstructor.newInstance(parametersValues.toArray(new Object[0]));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return tipoDeContato;
    }
}
