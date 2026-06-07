package br.com.exemplo.gastoscartao.service;

import br.com.exemplo.gastoscartao.dto.DespesaRequest;
import br.com.exemplo.gastoscartao.dto.DespesaResponse;
import br.com.exemplo.gastoscartao.entity.Cartao;
import br.com.exemplo.gastoscartao.entity.Despesa;
import br.com.exemplo.gastoscartao.exception.RecursoNaoEncontradoException;
import br.com.exemplo.gastoscartao.repository.DespesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final CartaoService cartaoService;

    public DespesaService(DespesaRepository despesaRepository, CartaoService cartaoService) {
        this.despesaRepository = despesaRepository;
        this.cartaoService = cartaoService;
    }

    @Transactional
    public DespesaResponse criar(DespesaRequest request) {
        Cartao cartao = cartaoService.buscarEntidadePorId(request.cartaoId());
        Despesa despesa = new Despesa();
        preencherDespesa(despesa, request, cartao);
        return toResponse(despesaRepository.save(despesa));
    }

    @Transactional(readOnly = true)
    public List<DespesaResponse> listar() {
        return despesaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<DespesaResponse> listarPorCartao(Long cartaoId) {
        cartaoService.buscarEntidadePorId(cartaoId);
        return despesaRepository.findByCartaoId(cartaoId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<DespesaResponse> listarPorPessoa(Long pessoaId) {
        return despesaRepository.findByPessoaId(pessoaId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<DespesaResponse> listarPorPessoaEPeriodo(Long pessoaId, LocalDate inicio, LocalDate fim) {
        return despesaRepository.findByPessoaIdAndPeriodo(pessoaId, inicio, fim).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public DespesaResponse buscarPorId(Long id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public DespesaResponse atualizar(Long id, DespesaRequest request) {
        Despesa despesa = buscarEntidadePorId(id);
        Cartao cartao = cartaoService.buscarEntidadePorId(request.cartaoId());
        preencherDespesa(despesa, request, cartao);
        return toResponse(despesa);
    }

    @Transactional
    public void excluir(Long id) {
        Despesa despesa = buscarEntidadePorId(id);
        despesaRepository.delete(despesa);
    }

    private Despesa buscarEntidadePorId(Long id) {
        return despesaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Despesa não encontrada com ID: " + id));
    }

    private void preencherDespesa(Despesa despesa, DespesaRequest request, Cartao cartao) {
        despesa.setCartao(cartao);
        despesa.setDescricao(request.descricao());
        despesa.setCategoria(request.categoria());
        despesa.setValor(request.valor());
        despesa.setDataCompra(request.dataCompra());
        despesa.setParcelas(request.parcelas());
    }

    private DespesaResponse toResponse(Despesa despesa) {
        return new DespesaResponse(
                despesa.getId(), despesa.getCartao().getId(), despesa.getCartao().getApelido(), despesa.getDescricao(),
                despesa.getCategoria(), despesa.getValor(), despesa.getDataCompra(), despesa.getParcelas(), despesa.getCriadoEm()
        );
    }
}
