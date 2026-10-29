package br.com.estudo.tarefasapi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {
    @Autowired MockMvc mvc;
    @Autowired com.fasterxml.jackson.databind.ObjectMapper json;
    @Test void crudFiltroERegras() throws Exception {
        String body="{\"titulo\": \"Estudar Spring\", \"descricao\": \"Revisar GET e POST\", \"concluida\": false}";
        String response=mvc.perform(post("/tarefas").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(header().exists("Location")).andReturn().getResponse().getContentAsString();
        long id=json.readTree(response).get("id").asLong();
        mvc.perform(get("/tarefas/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Estudar Spring"));
        mvc.perform(get("/tarefas").param("titulo","spring")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(id));
        mvc.perform(patch("/tarefas/"+id+"/concluida").contentType(MediaType.APPLICATION_JSON).content("{\"concluida\": true}")).andExpect(status().isOk()).andExpect(jsonPath("$.concluida").value(true));
        mvc.perform(put("/tarefas/"+id).contentType(MediaType.APPLICATION_JSON).content("{\"titulo\": \"Estudar Spring Atualizado\", \"descricao\": \"Revisar GET e POST\", \"concluida\": false}")).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Estudar Spring Atualizado"));
        mvc.perform(delete("/tarefas/"+id)).andExpect(status().isNoContent()).andExpect(content().string(""));
        mvc.perform(get("/tarefas/"+id)).andExpect(status().isNotFound());
        mvc.perform(delete("/tarefas/"+id)).andExpect(status().isNotFound());
    }
    @Test void validacaoEContratoHttp() throws Exception {
        mvc.perform(post("/tarefas").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.campos").isArray());
        mvc.perform(post("/tarefas").contentType(MediaType.APPLICATION_JSON).content("{")).andExpect(status().isBadRequest());
        mvc.perform(get("/tarefas/abc")).andExpect(status().isBadRequest());
        mvc.perform(post("/tarefas").contentType(MediaType.TEXT_PLAIN).content("teste")).andExpect(status().isUnsupportedMediaType());
        mvc.perform(get("/tarefas").accept(MediaType.TEXT_HTML)).andExpect(status().isNotAcceptable());
    }
}
