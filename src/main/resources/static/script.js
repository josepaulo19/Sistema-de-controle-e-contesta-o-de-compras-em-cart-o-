function toggleSenha() {
    const input = document.getElementById("senha");

    if (input.type === "password") {
        input.type = "text";
    } else {
        input.type = "password";
    }
}
const cpfInput = document.getElementById("cpf");

if (cpfInput) {
    cpfInput.addEventListener("input", function () {
        let valor = cpfInput.value;

        valor = valor.replace(/\D/g, "");
        valor = valor.substring(0, 11);

        valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
        valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
        valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

        cpfInput.value = valor;
    });
}
function entrar() {
    const cpf = document.getElementById("cpf").value; // Pega o valor do input
    localStorage.setItem("cpfDigitado", cpf);
    // redireciona para o dashboard
    window.location.href = "dashboard.html";
}
function validarLogin(event) {
    event.preventDefault();

    const cpf = document.getElementById("cpf").value;
    const senha = document.getElementById("senha").value;

    const cpfLimpo = cpf.replace(/\D/g, "");

    if (cpfLimpo.length !== 11) {
        alert("CPF inválido!");
        return;
    }

    if (senha.length < 4) {
        alert("Senha inválida!");
        return;
    }
    sessionStorage.setItem("cpfDigitado", cpf);

    window.location.href = "dashboard.html";
}

