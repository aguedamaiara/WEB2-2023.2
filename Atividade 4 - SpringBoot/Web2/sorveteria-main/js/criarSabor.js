document.getElementById('saborForm').addEventListener('submit', function(event) {
    event.preventDefault();
 
    var sabor = {
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('descricao').value,
    };
 
    fetch('http://localhost:8080/sabor', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sabor),
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        } else {
            return response.json();
        }
    })
    .then((data) => console.log('Success:', data))
    .catch((error) => console.error('Error:', error));
 });
 