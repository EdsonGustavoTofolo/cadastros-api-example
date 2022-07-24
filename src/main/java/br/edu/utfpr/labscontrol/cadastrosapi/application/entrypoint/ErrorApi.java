package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorApi {
    private int status;
    private LocalDateTime dataHora;
    private List<String> mensagens;
}
