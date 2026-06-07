function voltarPagina() {
    window.history.back();
}
function irPara(pagina) {
    if (pagina === "inicio") {
        window.location.href = "dashboard.html";
    }

    if (pagina === "cartoes") {
          window.location.href = "cartoes.html";
    }

    if (pagina === "seguranca") {
         alert("Você já está na segurança");
    }
     if (pagina === "Mais") {
         window.location.href = "Mais.html";
    }


}