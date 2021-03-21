package br.eckelp.lancamentoconta.app.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService implements IEncoder{

    private final PasswordEncoder encoder;

    public EncoderService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String texto){
        return encoder.encode(texto);
    }

}
