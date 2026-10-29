package br.com.estudo.tarefasapi;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/tarefas", produces="application/json")
public class TarefaController {
    private final TarefaModel model;
    public TarefaController(TarefaModel model) { this.model = model; }
    @PostMapping(consumes="application/json")
    public ResponseEntity<TarefaResponse> criar(@Valid @RequestBody TarefaRequest request) {
        Tarefa criado = model.criar(TarefaMapper.toEntity(request, null));
        return ResponseEntity.created(URI.create("/tarefas/"+criado.getId())).body(TarefaMapper.toResponse(criado));
    }
    @GetMapping
    public List<TarefaResponse> listar(@RequestParam(name="titulo", required=false) String titulo,
            @RequestParam(name="concluida", required=false) Boolean concluida) { return model.listar(titulo, concluida).stream().map(TarefaMapper::toResponse).toList(); }
    @GetMapping("/{id}")
    public TarefaResponse buscar(@PathVariable("id") Long id) { return TarefaMapper.toResponse(model.buscar(id).orElseThrow(() -> new RecursoNaoEncontradoException(id))); }
    @PutMapping(value="/{id}", consumes="application/json")
    public TarefaResponse atualizar(@PathVariable("id") Long id, @Valid @RequestBody TarefaRequest request) {
        return TarefaMapper.toResponse(model.atualizar(id, TarefaMapper.toEntity(request,id)).orElseThrow(() -> new RecursoNaoEncontradoException(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        if (!model.excluir(id)) throw new RecursoNaoEncontradoException(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(value="/{id}/concluida", consumes="application/json")
    public TarefaResponse alterarConcluida(@PathVariable("id") Long id, @Valid @RequestBody ConcluidaRequest request) {
        Tarefa atual = model.buscar(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        return TarefaMapper.toResponse(model.atualizar(id, new Tarefa(id, atual.getTitulo(), atual.getDescricao(), request.concluida()))
            .orElseThrow(() -> new RecursoNaoEncontradoException(id)));
    }
}
