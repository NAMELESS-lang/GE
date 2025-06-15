function notificacao(mensagem){
    let main = document.getElementById('principal');
    let form = document.getElementById('form_cadastro');
    let p_mensagem = document.createElement('p');
    p_mensagem.textContent = mensagem;
    main.insertBefore(p_mensagem, form);
}

function cadastrar(){
    let form = FormData(document.getElementById('form_cadastro'));
    let dados = {};
    form.forEach((element,key) => {
        dados[key] = element;
    });

    let requisicao = {
        method : POST,
        header: {'Content-Type':'application/json'},
        body: JSON.stringify(dados)
    }

    fetch('http:/localhost:8080/cadastrar',requisicao)
    .then(Response => {
        notificacao(Response.message);
    })
    .catch(Error => {
        notificacao(Error.message);
    })
}

const formulario_cadastro = document.getElementById('formulario_cadastro').addEventListener('submit',function(event){
    event.preventDefault();
    cadastrar();
})