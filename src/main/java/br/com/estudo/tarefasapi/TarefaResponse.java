package br.com.estudo.tarefasapi;

import java.math.BigDecimal;

public record TarefaResponse(Long id, String titulo, String descricao, Boolean concluida) {}
