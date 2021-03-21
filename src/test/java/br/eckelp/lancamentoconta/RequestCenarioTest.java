package br.eckelp.lancamentoconta;

import br.eckelp.lancamentoconta.app.security.controller.form.UsuarioForm;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

public class RequestCenarioTest {

    private MockMvc mockMvc;

    public RequestCenarioTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }


    public HttpHeaders headers(Usuario usuario) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + getToken(usuario));

        return headers;
    }

    private String getToken(Usuario usuario) throws Exception {

        UsuarioForm form = UsuarioForm.builder()
                .email(usuario.getEmail())
                .senha(UsuarioCenarioTest.SENHA_USUARIO)
                .build();

        URI uri = new URI("/auth");
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andReturn();

        String token = result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);

        return token;

    }

}
