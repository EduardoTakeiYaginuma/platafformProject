import os
from fastapi import FastAPI, HTTPException, Query, Request
import httpx

app = FastAPI(
    title="Microserviço Exchange",
    description="Microserviço que consome dados da economy-api para obter cotações e realizar conversões de moedas",
    version="1.0.0"
)

@app.get("/coin/{moeda1}/{moeda2}")
async def get_rate(moeda1: str, moeda2: str, request: Request):  # <-- injeta Request

    url = f"https://economia.awesomeapi.com.br/last/{moeda1}-{moeda2}"
    print(f"URL: {url}")

    try:
        # 3) (Opcional) repassar algum header ao chamar a API externa
        async with httpx.AsyncClient() as client:
            response = await client.get(url)  # ou: headers={"User-Agent": user_agent}


        if response.status_code != 200:
            raise HTTPException(
                status_code=500,
                detail="Erro ao acessar a economy-api para a moeda solicitada."
            )

        data_json = response.json()
        key = f"{moeda1.upper()}{moeda2.upper()}"
        if key not in data_json:
            raise HTTPException(status_code=500, detail="Chave de resposta não encontrada na API.")

        moeda_data = data_json[key]

        return {
            "buy": moeda_data.get("bid"),
            "sell": moeda_data.get("ask"),
            "date":   moeda_data.get("create_date"),
            "id-account": request.headers["id-account"]  # <-- repassa o header
        }

    except httpx.RequestError as exc:
        raise HTTPException(status_code=500, detail=f"Erro na requisição: {exc}")
    
if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)
