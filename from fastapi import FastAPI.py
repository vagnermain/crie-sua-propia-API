from fastapi import FastAPI

app = FastAPI()

# Exemplo de rota básica
@app.get("/")
async def read_root():
    return {"message": "API de CrossFit Competition"}

# Exemplo de endpoint assíncrono para listar participantes
@app.get("/participants/")
async def get_participants():
    participants = [
        {"name": "Alice", "score": 350},
        {"name": "Bob", "score": 300},
        {"name": "Charlie", "score": 320},
    ]
    return {"participants": participants}

# Exemplo de endpoint assíncrono para adicionar um novo participante
@app.post("/participants/")
async def add_participant(name: str, score: int):
    # Aqui você pode adicionar lógica para adicionar o participante ao sistema
    return {"message": f"Participant {name} added with score {score}"}

# Exemplo de endpoint assíncrono para registrar pontuação
@app.put("/participants/{participant_id}/score/")
async def update_score(participant_id: int, new_score: int):
    # Aqui você pode adicionar lógica para atualizar a pontuação do participante
    return {"message": f"Score updated for participant {participant_id} to {new_score}"}
