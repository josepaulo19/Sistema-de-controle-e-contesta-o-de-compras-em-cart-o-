document.addEventListener("DOMContentLoaded", function() {
    const container = document.getElementById("lista-de-compras-container");
    if (!container || typeof LISTA_DE_COMPRAS === 'undefined') return;

    // 1. Pega o cartão ativo
    const cartaoAtivo = sessionStorage.getItem("cartaoAtivo") || "1111";

    // 2. Filtra a lista global deixando passar apenas as deste cartão
    const comprasFiltradas = LISTA_DE_COMPRAS.filter(compra => compra.cartao === cartaoAtivo);

    container.innerHTML = "";

    // 3. Desenha apenas as compras filtradas
    comprasFiltradas.forEach(compra => {
        const card = document.createElement("div");
        card.className = "compra-card";
        card.innerHTML = `
            <div class="compra-topo">
                <div class="compra-info-esquerda">
                    <span class="compra-icone">${compra.icone}</span>
                    <div class="compra-detalhes-texto">
                        <strong>${compra.loja}</strong>
                        <p>${compra.data}</p>
                    </div>
                </div>
                <div class="compra-info-direita">
                    <span class="compra-valor">${compra.valor}</span>
                    <span class="status-aprovada">Aprovada</span>
                </div>
            </div>
            <div class="compra-pergunta">
                <p>Você reconhece essa compra?</p>
                <button class="btn-contestar" onclick="irParaContestacao('${compra.cartao}')">
                    ⓘ Não, contestar
                </button>
            </div>
        `;

        container.appendChild(card);
    });
});
function irParaContestacao(cartaoDaCompra) {
    // Garante que o sessionStorage lembre qual era o cartão dessa compra específica
    sessionStorage.setItem("cartaoAtivo", cartaoDaCompra);
    // Agora sim vai para a tela de contestação
    window.location.href = "contestar.html";
}
function irPara(pagina) {
    if (pagina === "inicio") {
        window.location.href = "dashboard.html";
    }

    if (pagina === "cartoes") {
         window.location.href = "cartoes.html";
    }

    if (pagina === "seguranca") {
         window.location.href = "seguranca.html";
    }
     if (pagina === "Mais") {
           window.location.href = "Mais.html";
    }
    
}