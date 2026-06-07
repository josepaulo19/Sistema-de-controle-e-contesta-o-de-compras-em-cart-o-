package br.com.exemplo.gastoscartao.controller;

import br.com.exemplo.gastoscartao.dto.PessoaRequest;
import br.com.exemplo.gastoscartao.dto.PessoaResponse;
import br.com.exemplo.gastoscartao.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@Valid @RequestBody PessoaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listar() {
        return ResponseEntity.ok(pessoaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaRequest request) {
        return ResponseEntity.ok(pessoaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
