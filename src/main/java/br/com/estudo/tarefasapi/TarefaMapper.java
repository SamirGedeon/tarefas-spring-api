package br.com.estudo.tarefasapi;

public final class TarefaMapper {
    private TarefaMapper() {}
    public static Tarefa toEntity(TarefaRequest r, Long id) { return new Tarefa(id, r.titulo().strip(), r.descricao().strip(), r.concluida()); }
    public static TarefaResponse toResponse(Tarefa item) { return new TarefaResponse(item.getId(), item.getTitulo(), item.getDescricao(), item.getConcluida()); }
}
