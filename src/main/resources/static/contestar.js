let opcaoSelecionadaTexto = "";

function selecionarOpcao(elemento) {
    // 1. Remove a classe 'selecionado' de todos os cards
    const todosOsCards = document.querySelectorAll('.opcao-card');
    todosOsCards.forEach(card => card.classList.remove('selecionado'));

    // 2. Adiciona a classe apenas ao card que foi clicado
    elemento.classList.add('selecionado');

    // 3. Guarda o texto do motivo selecionado
    opcaoSelecionadaTexto = elemento.querySelector('h3').innerText;
}

function enviarContestacao() {
    if (opcaoSelecionadaTexto === "") {
        alert("Por favor, selecione um motivo para a contestação.");
        return;
    }

    const numeroAleatorio = Math.floor(1000 + Math.random() * 9000);
    const protocoloGerado = `EXMP-${numeroAleatorio}`;

    const cpfLogado =
    sessionStorage.getItem("cpfDigitado");

let listaProtocolos =
JSON.parse(
    sessionStorage.getItem(`listaProtocolos_${cpfLogado}`)
) || [];

listaProtocolos.push(protocoloGerado);

sessionStorage.setItem(
    `listaProtocolos_${cpfLogado}`,
    JSON.stringify(listaProtocolos)
);

    alert(`Contestação enviada com sucesso!\n\nProtocolo: ${protocoloGerado}`);
    window.location.href = "dashboard.html";
}