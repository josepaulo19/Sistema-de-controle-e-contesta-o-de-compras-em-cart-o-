let opcaoSelecionadaTexto = "";

function selecionarOpcao(elemento) {

    const todosOsCards =
        document.querySelectorAll('.opcao-card');

    todosOsCards.forEach(card =>
        card.classList.remove('selecionado')
    );

    elemento.classList.add('selecionado');

    opcaoSelecionadaTexto =
        elemento.querySelector('h3').innerText;
}

function enviarContestacao() {

    if (opcaoSelecionadaTexto === "") {
        alert("Por favor, selecione um motivo para a contestação.");
        return;
    }

    const numeroAleatorio =
        Math.floor(1000 + Math.random() * 9000);

    const protocoloGerado =
        `EXMP-${numeroAleatorio}`;

    const cpfLogado =
        sessionStorage.getItem("cpfDigitado");

    let listaProtocolos =
        JSON.parse(
            localStorage.getItem(`listaProtocolos_${cpfLogado}`)
        ) || [];

    const novaContestacao = {
        protocolo: protocoloGerado,
        motivo: opcaoSelecionadaTexto,
        data: new Date().toLocaleDateString("pt-BR")
    };

    listaProtocolos.push(novaContestacao);

    localStorage.setItem(
        `listaProtocolos_${cpfLogado}`,
        JSON.stringify(listaProtocolos)
    );

    alert(
        `Contestação enviada com sucesso!\n\nProtocolo: ${protocoloGerado}`
    );

    window.location.href = "dashboard.html";
}