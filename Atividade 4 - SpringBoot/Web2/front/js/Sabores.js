window.onload = function () {
    fetch('http://localhost:8080/sabor')
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error('Error:', error));
};

function populateTable(data) {
    const table = document.getElementById('saborTable');

    data.forEach(item => {
        const row = table.insertRow();
        row.insertCell().innerHTML = item.codigo;
        row.insertCell().innerHTML = item.nome;
        row.insertCell().innerHTML = item.descricao;
        row.insertCell().innerHTML = `<button onclick="editItem(${item.codigo})">Edit</button> <button onclick="deleteItem(${item.codigo})">Delete</button>`;
    });
}

function editItem(codigo) {
    fetch(`http://localhost:8080/sabor/${codigo}`)
        .then(response => response.json())
        .then(data => {
            // Assuming you have form fields with these IDs
            document.getElementById('nome').value = data.nome;
            document.getElementById('descricao').value = data.descricao;
            document.getElementById('codigo').value = data.codigo; // Hidden field
        })
        .catch(error => console.error('Error:', error));
}

function saveItem() {
    const item = {
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('descricao').value,
        codigo: document.getElementById('codigo').value // Hidden field
    };

    fetch('http://localhost:8080/sabor', {
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
            document.getElementById('nome').value = '';
            document.getElementById('descricao').value = '';
            document.getElementById('codigo').value = ''; // Hidden field

            refreshTable();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteItem(codigo) {
    fetch(`http://localhost:8080/sabor/${codigo}`, {
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
    const table = document.getElementById('saborTable');
    table.innerHTML = '';

    // Fetch the updated data and repopulate the table
    fetch('http://localhost:8080/sabor')
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error('Error:', error));
}
