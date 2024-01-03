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
        row.insertCell().innerHTML = item.tipoSorvete.nome; // Assume que há uma propriedade "nome" em "tipoSorvete"
        row.insertCell().innerHTML = `<button onclick="editSorvete(${item.codigo})">Edit</button> <button onclick="deleteSorvete(${item.codigo})">Delete</button>`;
    });
}

function editSorvete(codigo) {
    fetch(`http://localhost:8080/sorvete/${codigo}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('dataCompra').value = data.dataCompra;
            document.getElementById('tipoSorvete').value = data.tipoSorvete.nome; // Assume que há uma propriedade "nome" em "tipoSorvete"
            document.getElementById('codigo').value = data.codigo;
        })
        .catch(error => console.error('Error:', error));
}

function saveSorvete() {
    const sorvete = {
        dataCompra: document.getElementById('dataCompra').value,
        tipoSorvete: { nome: document.getElementById('tipoSorvete').value }, // Assume que há uma propriedade "nome" em "tipoSorvete"
        codigo: document.getElementById('codigo').value
    };

    fetch('http://localhost:8080/sorvete', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sorvete),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(message => {
            const successMessageParagraph = document.querySelector('#successMessageText');
            successMessageParagraph.textContent = 'Alteração feita com sucesso!!';
            document.getElementById('successMessage').style.display = 'block';

            document.getElementById('dataCompra').value = '';
            document.getElementById('tipoSorvete').value = '';
            document.getElementById('codigo').value = '';

            refreshTable();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteSorvete(codigo) {
    fetch(`http://localhost:8080/sorvete/${codigo}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(message => {
            const successMessageParagraph = document.querySelector('#successMessageText');
            successMessageParagraph.textContent = 'Exclusão feita com sucesso!!';
            document.getElementById('successMessage').style.display = 'block';

            refreshTable();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function refreshTable() {
    const table = document.getElementById('sorveteTable');
    table.innerHTML = '';

    fetch('http://localhost:8080/sorvete')
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error('Error:', error));
}
