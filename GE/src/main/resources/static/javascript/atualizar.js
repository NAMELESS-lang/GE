function criar_atualizar(){
    let form = document.createElement('form');
    let label_nome_pesquisa = document.createElement('select');
    let opcoes = {
        "Codigo do produto": "codigo_produto",
        "Nome do produto" : "nome_produto",
        "Marca": "marca"
    }
    for(chave in opcoes){
        let opcao = document.createElement('option');
        opcao.value = opcoes[chave];
        opcao.textContent = chave;
        label_nome_pesquisa.appendChild(opcao);
    };

    let input_pesquisa = document.createElement('input');
    let button = document.createElement('button');
    button.textContent="Pesquisar";


    form.appendChild(label_nome_pesquisa);
    form.appendChild(input_pesquisa);
    form.appendChild(button);
    form.classList.add("form_pesquisa");

    let main = document.getElementById('principal');
    let form_atual = document.getElementById('form');
    let titulo_funcao = document.getElementById('titulo_funcao');
    titulo_funcao.textContent="Atualizar";
    main.removeChild(form_atual); 
    main.appendChild(form);
    return
}