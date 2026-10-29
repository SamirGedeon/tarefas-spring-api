package br.com.estudo.tarefasapi;

import java.math.BigDecimal;

public class Tarefa {
    private final Long id;
    private final String titulo;
    private final String descricao;
    private final Boolean concluida;
    public Tarefa(Long id, String titulo, String descricao, Boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
    }
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Boolean getConcluida() { return concluida; }
}
