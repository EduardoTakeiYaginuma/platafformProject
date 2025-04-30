# Use uma imagem base leve do Python
FROM python:3.9-slim

# Define o diretório de trabalho
WORKDIR /app

# Copie o arquivo de requisitos e instale as dependências
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copie o código da aplicação
COPY main.py .

# Exponha a porta padrão em que a aplicação será executada
EXPOSE 8080

# Define o comando para iniciar a aplicação com uvicorn na porta 8080
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080"]
