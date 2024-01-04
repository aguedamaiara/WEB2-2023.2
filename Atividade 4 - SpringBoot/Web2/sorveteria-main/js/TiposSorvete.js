window.onload = function () {
    fetch('http://localhost:8080/tipoSorvete')
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error('Error:', error));
};

function populateTable(data) {
    const table = document.getElementById('iceCreamTable');

    data.forEach(item => {
        const row = table.insertRow();
        row.insertCell().innerHTML = item.tipo;
        row.insertCell().innerHTML = item.quantBolas;
        row.insertCell().innerHTML = item.peso;
        row.insertCell().innerHTML = item.descricao;
        row.insertCell().innerHTML = item.valor;
        row.insertCell().innerHTML = `<button onclick="editItem(${item.codigo})">Edit</button> <button onclick="deleteItem(${item.codigo})">Delete</button>`;
    });
}
function editItem(codigo) {
    fetch(`http://localhost:8080/tipoSorvete/${codigo}`)
        .then(response => response.json())
        .then(data => {
            // Assuming you have form fields with these IDs
            document.getElementById('tipo').value = data.tipo;
            document.getElementById('quantBolas').value = data.quantBolas;
            document.getElementById('peso').value = data.peso;
            document.getElementById('descricao').value = data.descricao;
            document.getElementById('valor').value = data.valor;
            document.getElementById('codigo').value = data.codigo; // Hidden field
        })
        .catch(error => console.error('Error:', error));
}

function saveItem() {
    const item = {
        tipo: document.getElementById('tipo').value,
        quantBolas: document.getElementById('quantBolas').value,
        peso: document.getElementById('peso').value,
        descricao: document.getElementById('descricao').value,
        valor: document.getElementById('valor').value,
        codigo: document.getElementById('codigo').value // Hidden field
    };

    fetch('http://localhost:8080/tipoSorvete', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(item),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Expect a text response
        })
        .then(message => {
            // Show success message
            const successMessageParagraph = document.querySelector('#successMessageText');
            successMessageParagraph.textContent = 'Alteração feita com sucesso!!';
            document.getElementById('successMessage').style.display = 'block';

            // Clear form fields
            document.getElementById('tipo').value = '';
            document.getElementById('quantBolas').value = '';
            document.getElementById('peso').value = '';
            document.getElementById('descricao').value = '';
            document.getElementById('valor').value = '';
            document.getElementById('codigo').value = ''; // Hidden field

            refreshTable();

        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteItem(codigo) {
    fetch(`http://localhost:8080/tipoSorvete/${codigo}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Expect a text response
        })
        .then(message => {
            // Show success message
            const successMessageParagraph = document.querySelector('#successMessageText');
            successMessageParagraph.textContent = 'Exclusão feita com sucesso!!';
            document.getElementById('successMessage').style.display = 'block';

            // Refresh the table after deletion
            refreshTable();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function refreshTable() {
    // Clear the existing table rows
    const table = document.getElementById('iceCreamTable');
    table.innerHTML = '';

    // Fetch the updated data and repopulate the table
    fetch('http://localhost:8080/tipoSorvete')
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error('Error:', error));
}

