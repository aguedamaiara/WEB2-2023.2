window.onload = function () {
    fetch('http://localhost:8080/sorvete')
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error('Error:', error));
};

function populateTable(data) {
    const table = document.getElementById('sorveteTable');

    data.forEach(item => {
        const row = table.insertRow();
        row.insertCell().innerHTML = item.codigo;
        row.insertCell().innerHTML = item.dataCompra;
    });
}
