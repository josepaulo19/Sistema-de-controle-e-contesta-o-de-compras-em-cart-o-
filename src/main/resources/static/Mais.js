// Assim que a página carrega, ele executa essa função
document.addEventListener("DOMContentLoaded", function() {
    
    // Busca no navegador se existe algum CPF salvo
    let cpfSalvo = sessionStorage.getItem("cpfDigitado");
    
    // Se existir, ele troca o texto lá no HTML
    if (cpfSalvo) {
        document.getElementById("cpf-tela").innerText = cpfSalvo;
    }
});

// Função do botão Sair da conta
function sairDaConta() {
   // LIMPEZA COMPLETA: Apaga o CPF, os cartões ativos e TODOS os protocolos de uma vez só
    sessionStorage.clear();
    
    // Redireciona para a tela de login 
    window.location.href = "index.html"; 
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
          alert("Você já está no Mais");
    }
}