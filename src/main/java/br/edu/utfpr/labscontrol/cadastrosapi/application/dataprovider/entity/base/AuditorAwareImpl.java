package br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.entity.base;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    // TODO: ajustar para pegar o usuario logado
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("edson.tofolo");
    }
}
