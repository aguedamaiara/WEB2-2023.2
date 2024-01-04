
let tipoSorveteSelecionado = '';
let saboresSelecionados = [];
let maxQuantBolas = 0;

// Buscar os tipos de sorvetes do servidor
window.onload = function () {
    fetch('http://localhost:8080/tipoSorvete')
        .then(response => response.json())
        .then(data => populateTipoSorveteDropdown(data))
        .catch(error => console.error('Error:', error));

    // Iniciar o processo de busca de sabores
    buscarSabores();
};

function populateTipoSorveteDropdown(data) {
    const tipoSorveteDropdown = document.getElementById('tipoSorvete');
    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.codigo;
        option.textContent = item.tipo;
        tipoSorveteDropdown.appendChild(option);
    });
}

function buscarSabores() {
    fetch('http://localhost:8080/sabor')
        .then(response => response.json())
        .then(data => populateSaboresList(data))
        .catch(error => console.error('Error:', error));
}

function populateSaboresList(data) {
    const saboresList = document.getElementById('saboresList');

    data.forEach(item => {
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.name = 'sabor';
        checkbox.value = item.codigo;

        const label = document.createElement('label');
        label.appendChild(checkbox);
        label.appendChild(document.createTextNode(item.nome));

        saboresList.appendChild(label);
    });

}

function proximoPasso() {
    if (document.getElementById('step1').style.display !== 'none') {

        tipoSorveteSelecionado = document.getElementById('tipoSorvete').options[document.getElementById('tipoSorvete').selectedIndex].text;
        const codigoTipoSorvete = document.getElementById('tipoSorvete').value;

        // Adicione a verificação da quantidade máxima de bolas permitidas
        fetch(`http://localhost:8080/tipoSorvete/${codigoTipoSorvete}/quantBolas`)
            .then(response => response.json())
            .then(data => {
                maxQuantBolas = data;
                if (saboresSelecionados.length > maxQuantBolas) {
                    alert(`Seu tipo de sorvete permite no máximo ${maxQuantBolas} bolas.`);
                    return;
                }
                document.getElementById('step1').style.display = 'none';
                document.getElementById('step2').style.display = 'block';
            })
            .catch(error => console.error('Error:', error));
    } else if (document.getElementById('step2').style.display !== 'none') {
       // Atualize saboresSelecionados antes de fazer a verificação
        const checkboxes = document.getElementsByName('sabor');
        saboresSelecionados = Array.from(checkboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => {
                return {
                    codigo: parseInt(checkbox.value),
                    nome: checkbox.nextSibling.textContent
                };
            });

        // Adicione a verificação da quantidade máxima de bolas permitidas
        if (saboresSelecionados.length > maxQuantBolas) {
            alert(`Seu tipo de sorvete permite no máximo ${maxQuantBolas} bolas.`);
            return;
        }


        document.getElementById('step2').style.display = 'none';
        document.getElementById('step3').style.display = 'block';
        exibirEscolhas();
    }
}


function exibirEscolhas() {
    document.getElementById('tipoEscolhido').innerText = `Tipo de sorvete: ${tipoSorveteSelecionado}`;
    document.getElementById('saboresEscolhidos').innerText = `Sabores escolhidos: ${saboresSelecionados.map(sabor => sabor.nome).join(', ')}`;
}

function confirmarEscolha() {
    // Montar o objeto de sorvete para enviar ao servidor
    const sorvete = {
        dataCompra: new Date().toISOString().split('T')[0], // Data atual
        sabores: saboresSelecionados,
        tipoSorvete: {
            codigo: parseInt(document.getElementById('tipoSorvete').value),
            tipo: tipoSorveteSelecionado
            // Adicione outros atributos do tipo de sorvete se necessário
        }
    };

    // Enviar dados para o servidor
    fetch('http://localhost:8080/sorvete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(sorvete)
    })
        .then(response => response.json())
        .then(data => {
            // Lógica adicional após o sucesso (por exemplo, exibir mensagem)
            alert('Sorvete criado com sucesso!');
        })
        .catch(error => {
            // Lógica de tratamento de erro
            console.error('Error:', error);
        });

    // Reiniciar o processo
    reiniciarProcesso();
}

function reiniciarProcesso() {
    tipoSorveteSelecionado = '';
    saboresSelecionados = [];
    document.getElementById('tipoSorvete').value = 'casquinha';
    const checkboxes = document.getElementsByName('sabor');
    checkboxes.forEach(checkbox => checkbox.checked = false);
    document.getElementById('step3').style.display = 'none';
    document.getElementById('step1').style.display = 'block';
}