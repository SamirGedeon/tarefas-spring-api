package br.com.estudo.tarefasapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("memoria")
public class TarefaModelMemoria extends ArmazenamentoEmMemoria {  }
