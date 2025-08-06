db = db.getSiblingDB('pi2');

db.createCollection('horario_funcionamento');

// Insere múltiplos documentos (dados) na coleção 'jazigos'.
db.horario_funcionamento.insertMany([
    {
        "id": 1,
        "diaSemana": "Segunda-Feira",
        "abertura": "07:00",
        "fechamento": "18:00",
        "fechado": false
    },
    {
        "id": 2,
        "diaSemana": "Terça-Feira",
        "abertura": "07:00",
        "fechamento": "18:00",
        "fechado": false
    },
    {
        "id": 3,
        "diaSemana": "Quarta-Feira",
        "abertura": "07:00",
        "fechamento": "18:00",
        "fechado": false
    },
    {
        "id": 4,
        "diaSemana": "Quinta-Feira",
        "abertura": "07:00",
        "fechamento": "18:00",
        "fechado": false
    },
    {
        "id": 5,
        "diaSemana": "Sexta-Feira",
        "abertura": "07:00",
        "fechamento": "18:00",
        "fechado": false
    },
    {
        "id": 6,
        "diaSemana": "Sábado",
        "abertura": "08:00",
        "fechamento": "16:00",
        "fechado": false
    },
    {
        "id": 7,
        "diaSemana": "Domingo",
        "abertura": "08:00",
        "fechamento": "16:00",
        "fechado": true
    },
    {
        "id": 8,
        "diaSemana": "Feriados",
        "abertura": "12:00",
        "fechamento": "16:00",
        "fechado": true
    }
]);