package br.com.exemplo.gastoscartao.service;

import br.com.exemplo.gastoscartao.dto.CartaoRequest;
import br.com.exemplo.gastoscartao.dto.CartaoResponse;
import br.com.exemplo.gastoscartao.entity.Cartao;
import br.com.exemplo.gastoscartao.entity.Pessoa;
import br.com.exemplo.gastoscartao.exception.RecursoNaoEncontradoException;
import br.com.exemplo.gastoscartao.repository.CartaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final PessoaService pessoaService;

    public CartaoService(CartaoRepository cartaoRepository, PessoaService pessoaService) {
        this.cartaoRepository = cartaoRepository;
        this.pessoaService = pessoaService;
    }

    @Transactional
    public CartaoResponse criar(CartaoRequest request) {
        Pessoa pessoa = pessoaService.buscarEntidadePorId(request.pessoaId());
        Cartao cartao = new Cartao();
        preencherCartao(cartao, request, pessoa);
        return toResponse(cartaoRepository.save(cartao));
    }

    @Transactional(readOnly = true)
    public List<CartaoResponse> listar() {
        return cartaoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CartaoResponse> listarPorPessoa(Long pessoaId) {
        pessoaService.buscarEntidadePorId(pessoaId);
        return cartaoRepository.findByPessoaId(pessoaId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CartaoResponse buscarPorId(Long id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public CartaoResponse atualizar(Long id, CartaoRequest request) {
        Cartao cartao = buscarEntidadePorId(id);
        Pessoa pessoa = pessoaService.buscarEntidadePorId(request.pessoaId());
        preencherCartao(cartao, request, pessoa);
        return toResponse(cartao);
    }

    @Transactional
    public void excluir(Long id) {
        Cartao cartao = buscarEntidadePorId(id);
        cartaoRepository.delete(cartao);
    }

    public Cartao buscarEntidadePorId(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado com ID: " + id));
    }

    private void preencherCartao(Cartao cartao, CartaoRequest request, Pessoa pessoa) {
        cartao.setPessoa(pessoa);
        cartao.setApelido(request.apelido());
        cartao.setBandeira(request.bandeira());
        cartao.setUltimosDigitos(request.ultimosDigitos());
        cartao.setLimite(request.limite());
        cartao.setDiaFechamento(request.diaFechamento());
        cartao.setDiaVencimento(request.diaVencimento());
    }

    private CartaoResponse toResponse(Cartao cartao) {
        return new CartaoResponse(
                cartao.getId(), cartao.getPessoa().getId(), cartao.getPessoa().getNome(), cartao.getApelido(),
                cartao.getBandeira(), cartao.getUltimosDigitos(), cartao.getLimite(), cartao.getDiaFechamento(),
                cartao.getDiaVencimento(), cartao.getCriadoEm()
        );
    }
}
