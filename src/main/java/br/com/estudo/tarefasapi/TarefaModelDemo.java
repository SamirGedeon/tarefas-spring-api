package br.com.estudo.tarefasapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("demo")
public class TarefaModelDemo extends ArmazenamentoEmMemoria { public TarefaModelDemo() { criar(new Tarefa(null, "Estudar Spring", "Revisar GET e POST", false)); } }
