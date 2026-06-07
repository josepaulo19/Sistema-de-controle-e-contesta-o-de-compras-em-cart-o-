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
async function validarLogin(event) {
    console.log("FUNÇÃO LOGIN EXECUTOU");
    event.preventDefault();
      
    const cpf = document.getElementById("cpf").value;
    const senha = document.getElementById("senha").value;

    try {

        const resposta = await fetch(
            "http://localhost:8080/api/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    cpf: cpf,
                    senha: senha
                })
            }
        );

        if (resposta.ok) {

    const usuario = await resposta.json();
    console.log("Resposta do backend:", usuario);

    sessionStorage.setItem("cpfDigitado", usuario.cpf);
    sessionStorage.setItem("nomeUsuario", usuario.nome);
    console.log("Nome salvo:", sessionStorage.getItem("nomeUsuario"));

    window.location.href = "dashboard.html";



        } else {

            alert("CPF ou senha inválidos");

        }

    } catch (erro) {

        console.error(erro);
        alert("Não foi possível conectar ao servidor");

    }
}
