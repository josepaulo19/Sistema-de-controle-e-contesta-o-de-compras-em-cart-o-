document.addEventListener("DOMContentLoaded", function () {

    const cpfLogado = sessionStorage.getItem("cpfDigitado") || "";

    const cpfLimpo = cpfLogado.replace(/\D/g, "");

    if (cpfLimpo.length >= 4) {

        const finalGold = cpfLimpo.slice(-4);

        const finalPlatinum = String(
            (parseInt(finalGold) + 1567) % 10000
        ).padStart(4, "0");

        document.getElementById("cartao-gold").innerText =
            `•••• •••• •••• ${finalGold}`;

        document.getElementById("cartao-platinum").innerText =
            `•••• •••• •••• ${finalPlatinum}`;
    }

});
function selecionarCartaoCredito(finalCartao) {
    // Salva qual cartão está ativo na sessão
    sessionStorage.setItem("cartaoAtivo", finalCartao);
    // Vai para o Dashboard
    window.location.href = "dashboard.html";
}
function irPara(pagina) {
    if (pagina === "inicio") {
        window.location.href = "dashboard.html";
    }

    if (pagina === "cartoes") {
         alert("Você já está nos cartões");
         return;
    }

    if (pagina === "seguranca") {
         window.location.href = "seguranca.html";
    }
     if (pagina === "Mais") {
           window.location.href = "Mais.html";
    }
}