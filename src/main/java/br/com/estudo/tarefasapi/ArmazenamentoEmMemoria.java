package br.com.estudo.tarefasapi;

import java.util.*;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class ArmazenamentoEmMemoria implements TarefaModel {
    private final Map<Long, Tarefa> itens = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();
    public synchronized Tarefa criar(Tarefa entrada) {
        Long id = sequencia.incrementAndGet();
        Tarefa criado = new Tarefa(id, entrada.getTitulo(), entrada.getDescricao(), entrada.getConcluida());
        itens.put(id, criado);
        return criado;
    }
    public synchronized List<Tarefa> listar(String titulo, Boolean concluida) {
        
        return itens.values().stream()
                        .filter(item -> titulo == null || titulo.isBlank() || item.getTitulo().toLowerCase(Locale.ROOT).contains(titulo.strip().toLowerCase(Locale.ROOT)))
            .filter(item -> concluida == null || concluida.equals(item.getConcluida()))
            .toList();
    }
    public synchronized Optional<Tarefa> buscar(Long id) { return Optional.ofNullable(itens.get(id)); }
    public synchronized Optional<Tarefa> atualizar(Long id, Tarefa entrada) {
        if (!itens.containsKey(id)) return Optional.empty();
        Tarefa atualizado = new Tarefa(id, entrada.getTitulo(), entrada.getDescricao(), entrada.getConcluida());
        itens.put(id, atualizado);
        return Optional.of(atualizado);
    }
    public synchronized boolean excluir(Long id) { return itens.remove(id) != null; }
}
