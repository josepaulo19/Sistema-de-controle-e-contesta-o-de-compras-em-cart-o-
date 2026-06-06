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