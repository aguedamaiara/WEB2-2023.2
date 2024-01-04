document.getElementById('sorveteForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var tipoSorvete = {
        tipo: document.getElementById('tipo').value,
        quantBolas: document.getElementById('quantBolas').value,
        peso: document.getElementById('peso').value,
        descricao: document.getElementById('descricao').value,
        valor: document.getElementById('valor').value
    };

    fetch('http://localhost:8080/tipoSorvete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(tipoSorvete),
    })
    .then(response => {
        if (response.ok) {
            alert('TipoSorvete criado com sucesso!');
            return response.json();
        } else {
            throw new Error('Falha ao criar TipoSorvete');
        }
    })
    .then(data => {
        console.log('Sucesso:', data);
    });
});
