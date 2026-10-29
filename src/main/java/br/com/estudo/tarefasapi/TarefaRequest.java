package br.com.estudo.tarefasapi;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

// DTO de entrada; ID é gerado no servidor.
public record TarefaRequest(
    @NotBlank(message="titulo é obrigatório") @Size(max=120) String titulo,
    @NotBlank(message="descricao é obrigatório") @Size(max=500) String descricao,
    @NotNull Boolean concluida
) {}
