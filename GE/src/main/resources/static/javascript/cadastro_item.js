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


function criar_cadastro(){
    let main = document.getElementById("principal");
    let formulario_cadastro = document.createElement('form');
    let div1, div2 = document.createElement('div');

    campos_input_div1 = {
        "Nome do produto" : "nome_produto",
        "Data de validade" : "data_validade",
        "Marca" : "marca",
        "Quantidade" : "quantidade"}

    campos_input_div2 = {
        "Peso do produto" : "peso_produto",
        "Unidade" : "unidade",
        "Valor" : "valor"
    }

    campos_input_div1.forEach((chave, valor) => {
        let label = document.createElement('label');
        label.textContent = chave;
        label.setAttribute("for", valor);

        let input = document.createElement('input');
        if(key == "nome_produto" || key == "marca"){
                input.type = "text";
        }else if(key == "data_validade"){
            input.type = "date";
        }else{
            input.type = "number";
        }
        div1.appendChild(label, input);
    });


    campos_input_div2.forEach((chave, valor) =>{
        if(key != "undidade"){
            let label = document.createElement('label');
            label.setAttribute("for", valor);
            label.textContent = chave;

            let input = document.createElement('input');
            input.type = "number";

            div2.appendChild(label, input);
        }else{
            opcoes = ["Kg","g","L","ml","Un"];
            let select = document.createElement('select');
            
            opcoes.forEach(valor => {
                let option = document.createElement('option');
                option.value = valor;
                option.textContent = valor;
                select.appendChild(option);
            });
            div2.appendChild(select);
        }
    });

    formulario_cadastro.appendChild(div1, div2);
    main.appendChild(formulario_cadastro);
}
