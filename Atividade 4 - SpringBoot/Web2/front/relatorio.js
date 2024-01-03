function carregarRelatorio(endpoint, divId) {
    fetch(endpoint)
        .then(response => response.json())
        .then(data => exibirRelatorio(data, divId))
        .catch(error => console.error('Erro ao carregar relatório:', error));
}

function exibirRelatorio(relatorio, divId) {
    var html = '<table>';
    html += '<tr><th>';

    if (divId === 'relatorioSabor') {
        html += 'Sabor</th><th>Quantidade Vendida</th></tr>';

        relatorio.forEach(item => {
            html += '<tr>';
            html += '<td>' + item.sabor + '</td>';
            html += '<td>' + item.quantidadeVendida + '</td>';
            html += '</tr>';
        });
    } else if (divId === 'relatorioDia') {
        html += 'Dia</th><th>Quantidade Vendida</th></tr>';

        relatorio.forEach(item => {
            html += '<tr>';
            html += '<td>' + item.dia + '</td>';
            html += '<td>' + item.quantidadeVendida + '</td>';
            html += '</tr>';
        });
    }

    html += '</table>';
    document.getElementById(divId).innerHTML = html;
}
