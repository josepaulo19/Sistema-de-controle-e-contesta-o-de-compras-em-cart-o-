// EXECUTA AUTOMATICAMENTE ASSIM QUE O DASHBOARD ABRE NA TELA
document.addEventListener("DOMContentLoaded", function() {

    const nomeUsuario = sessionStorage.getItem("nomeUsuario");
    if (nomeUsuario && document.getElementById("nomeUsuario")) {
        document.getElementById("nomeUsuario").innerText = nomeUsuario;
    }
    
    const cpfLogado = sessionStorage.getItem("cpfDigitado") || "";
    const cpfLimpo = cpfLogado.replace(/\D/g, "");

    const elementoTransacoes = document.getElementById("qtd-transacoes");

    if (typeof LISTA_DE_COMPRAS !== "undefined") {
        const comprasFiltradas = LISTA_DE_COMPRAS.filter(compra => 
            String(compra.cpf).startsWith(cpfLimpo)
        );

        if (elementoTransacoes) {
            elementoTransacoes.innerText = comprasFiltradas.length;
        }

        const elementoAlertaCompra = document.getElementById("dados-compra-recente");
        if (elementoAlertaCompra && comprasFiltradas.length > 0) {
            const ultimaCompra = comprasFiltradas[comprasFiltradas.length - 1];
            elementoAlertaCompra.innerText = `${ultimaCompra.loja} - ${ultimaCompra.valor}`;
        }
    }

    // Carrega os protocolos do sessionStorage baseando-se no CPF limpo
    let listaProtocolos = JSON.parse(localStorage.getItem(`listaProtocolos_${cpfLimpo}`)) || [];
    const elementoContador = document.getElementById("qtd-contestadas");
    const elementoStatus = document.getElementById("status-contestações");

    if (listaProtocolos.length > 0) {
        if (elementoContador) elementoContador.innerText = listaProtocolos.length;
        if (elementoStatus) {
            if (listaProtocolos.length === 1) {
                elementoStatus.innerText = "Você possui 1 protocolo em análise. Clique para ver.";
            } else {
                elementoStatus.innerText = `Você possui ${listaProtocolos.length} protocolos em análise. Clique para ver.`;
            }
        }
    } else {
        if (elementoContador) elementoContador.innerText = "0";
        if (elementoStatus) elementoStatus.innerText = "Nenhuma contestação ativa";
    }
});

// FUNÇÃO PARA EXIBIR A LISTA COM O MODELO E MOTIVOS FORMATADOS
function abrirListaProtocolos() {
    const cpfLogado = sessionStorage.getItem("cpfDigitado") || "";
    const cpfLimpo = cpfLogado.replace(/\D/g, "");
     console.log("CPF LOGADO:", cpfLogado);
    console.log("CPF LIMPO:", cpfLimpo);

    console.log("CHAVE:", `listaProtocolos_${cpfLimpo}`);

    console.log(
        "DADOS:",
        localStorage.getItem(`listaProtocolos_${cpfLimpo}`)
    );  

    let listaProtocolos = JSON.parse(localStorage.getItem(`listaProtocolos_${cpfLimpo}`)) || [];
     console.log("LISTA:", listaProtocolos);
    
    if (listaProtocolos.length === 0) {
        alert("Você não possui nenhuma contestação ativa no momento.");
        return;
    }

    const ul = document.getElementById("lista-protocolos-ul");
    ul.innerHTML = ""; // Limpa duplicados

   listaProtocolos.forEach(contestacao => {

    let li = document.createElement("li");

    li.innerHTML = `
        <div>
            <strong>Protocolo:</strong>
            ${contestacao.protocolo}
            <br>

            <strong>Motivo:</strong>
            ${contestacao.motivo}
            <br>

            <strong>Data:</strong>
            ${contestacao.data}
        </div>
    `;

    ul.appendChild(li);
});

    document.getElementById("modal-protocolos").style.display = "flex";
}

function fecharListaProtocolos() {
    document.getElementById("modal-protocolos").style.display = "none";
}

function selecionarCartao() {
    window.location.href = "cartoes.html";
}

function abrirAlerta(tipo) {
    if (tipo === "login") alert("Detalhes do acesso à conta");
    if (tipo === "contestacao") alert("Detalhes da contestação");
}

function irPara(pagina) {
    if (pagina === "inicio") alert("Você já está na tela inicial");
    if (pagina === "cartoes") window.location.href = "cartoes.html";
    if (pagina === "seguranca") window.location.href = "seguranca.html";
    if (pagina === "Mais") window.location.href = "Mais.html";
    if (pagina === "compras") window.location.href = "compras.html";
}