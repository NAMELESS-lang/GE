function notificacao(mensagem){
    let main = document.getElementById('principal');
    let form = document.getElementById('form_cadastro');
    let p_mensagem = document.createElement('p');
    p_mensagem.textContent = mensagem;
    main.insertBefore(p_mensagem, form);
}

function cadastrar(){
    let form = new FormData(document.getElementById('form_cadastro'));
    let dados = {};
    form.forEach((element,key) => {
        dados[key] = element;
    });
    let data = new Date(dados['data_validade']);
    dados['data_validade'] = data.getTime();
    const requisicao = {
        method : "POST",
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(dados)
    }

    fetch('http://localhost:8080/cadastrar',requisicao) // Cria uma promise que fica aberta até o navegador receber uma resposta do servidor
	.then(response => { // Quando o navegador recebe a resposta a promise é resolvida e o corpo da resposta vira o response
	        
	        return response.text(); // Quando dou este return com o texto da resposta, o .then(data=>) é chamado
	    })
    .then(data => {
        notificacao(data); // Aqui fica o texto do corpo da resposta
    })
    .catch(error => {
        notificacao(error); // Caso seja lançada uma exceção, ele pula direto aqui
    })
}

document.getElementById('form_cadastro').addEventListener('submit',function(event){
    event.preventDefault();
    cadastrar();
})