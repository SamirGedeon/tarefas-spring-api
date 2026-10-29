package br.com.estudo.tarefasapi;

import jakarta.validation.constraints.*;
public record ConcluidaRequest(@NotNull Boolean concluida) {}
