package br.com.exemplo.gastoscartao.controller;

import br.com.exemplo.gastoscartao.dto.CartaoRequest;
import br.com.exemplo.gastoscartao.dto.CartaoResponse;
import br.com.exemplo.gastoscartao.service.CartaoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<CartaoResponse> criar(@Valid @RequestBody CartaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<CartaoResponse>> listar(@RequestParam(required = false) Long pessoaId) {
        if (pessoaId != null) {
            return ResponseEntity.ok(cartaoService.listarPorPessoa(pessoaId));
        }
        return ResponseEntity.ok(cartaoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cartaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody CartaoRequest request) {
        return ResponseEntity.ok(cartaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cartaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
