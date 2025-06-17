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

    const requisicao = {
        method : "POST",
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(dados)
    }

    fetch('http://localhost:8080/cadastrar',requisicao)
	.then(response => {
	        
	        return response.text(); // <--- ONDE A "CORREÇÃO" REALMENTE ACONTECE
	    })
    .then(data => {
        notificacao(data);
    })
    .catch(error => {
        notificacao(error);
    })
}

document.getElementById('form_cadastro').addEventListener('submit',function(event){
    event.preventDefault();
    cadastrar();
})