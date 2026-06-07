// EXECUTA AUTOMATICAMENTE ASSIM QUE O DASHBOARD ABRE NA TELA
document.addEventListener("DOMContentLoaded", function() {

        const nomeUsuario = sessionStorage.getItem("nomeUsuario");

    if (nomeUsuario) {
        document.getElementById("nomeUsuario").innerText = nomeUsuario;
    }
    
    const cpfLogado =
    sessionStorage.getItem("cpfDigitado");

const elementoTransacoes =
    document.getElementById("qtd-transacoes");

if (typeof LISTA_DE_COMPRAS !== "undefined") {

    const comprasFiltradas =
        LISTA_DE_COMPRAS.filter(
            compra => compra.cpf === cpfLogado
        );

    if (elementoTransacoes) {
        elementoTransacoes.innerText =
            comprasFiltradas.length;
    }

    const elementoAlertaCompra =
        document.getElementById("dados-compra-recente");

    if (
        elementoAlertaCompra &&
        comprasFiltradas.length > 0
    ) {

        const ultimaCompra =
            comprasFiltradas[
                comprasFiltradas.length - 1
            ];

        elementoAlertaCompra.innerText =
            `${ultimaCompra.loja} - ${ultimaCompra.valor}`;
    }

    }
let listaProtocolos =
    JSON.parse(
        sessionStorage.getItem(`listaProtocolos_${cpfLogado}`)
    ) || [];

const elementoContador =
    document.getElementById("qtd-contestadas");

const elementoStatus =
    document.getElementById("status-contestações");

    if (listaProtocolos.length > 0) {
        if (elementoContador) {
            elementoContador.innerText = listaProtocolos.length;
        }
        if (elementoStatus) {
            if (listaProtocolos.length === 1) {
                elementoStatus.innerText = "Você possui 1 protocolo em análise. Clique para ver.";
            } else {
                elementoStatus.innerText = `Você possui ${listaProtocolos.length} protocolos em análise. Clique para ver.`;
            }
        }
    } else {
        // Se estiver vazia, garante que exiba zerado
        if (elementoContador) elementoContador.innerText = "0";
        if (elementoStatus) elementoStatus.innerText = "Nenhuma contestação ativa";
    }
});
function abrirListaProtocolos() {
   const cpfLogado =
sessionStorage.getItem("cpfDigitado");

let listaProtocolos =
JSON.parse(
    sessionStorage.getItem(`listaProtocolos_${cpfLogado}`)
) || [];
    
    // Só abre se houver algum protocolo criado para este cartão
    if (listaProtocolos.length === 0) {
        alert("Você não possui nenhuma contestação ativa para este cartão no momento.");
        return;
    }

    const ul = document.getElementById("lista-protocolos-ul");
    ul.innerHTML = ""; // Limpa a lista antes de desenhar para não duplicar

    // Cria um item de lista <li> para cada protocolo salvo no array
    listaProtocolos.forEach(protocolo => {
        let li = document.createElement("li");
        li.innerText = `Protocolo ${protocolo}`;
        ul.appendChild(li);
    });

    // Mostra a janelinha na tela mudando o display de 'none' para 'flex'
    document.getElementById("modal-protocolos").style.display = "flex";
}

// FUNÇÃO PARA FECHAR O MODAL
function fecharListaProtocolos() {
    document.getElementById("modal-protocolos").style.display = "none";
}

function selecionarCartao() {
    window.location.href = "cartoes.html";
}

function abrirAlerta(tipo) {
    if (tipo === "login") {
        alert("Detalhes do acesso à conta");
    }
    if (tipo === "contestacao") {
        alert("Detalhes da contestação");
    }
}

function irPara(pagina) {
    if (pagina === "inicio") {
        alert("Você já está na tela inicial");
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
    if (pagina === "compras"){
        window.location.href = "compras.html";
    }
}
