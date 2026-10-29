package br.com.estudo.tarefasapi;

import java.util.*;
import java.math.BigDecimal;
public interface TarefaModel {
    Tarefa criar(Tarefa entrada);
    List<Tarefa> listar(String titulo, Boolean concluida);
    Optional<Tarefa> buscar(Long id);
    Optional<Tarefa> atualizar(Long id, Tarefa entrada);
    boolean excluir(Long id);
}
