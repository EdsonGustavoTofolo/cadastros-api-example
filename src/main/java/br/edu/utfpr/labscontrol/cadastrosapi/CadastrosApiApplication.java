package br.edu.utfpr.labscontrol.cadastrosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
@EnableFeignClients(basePackages = "br.edu.utfpr.labscontrol.cadastrosapi.application.dataprovider.client")
public class CadastrosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadastrosApiApplication.class, args);
    }

}
