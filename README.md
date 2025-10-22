# FIAP - Checkpoint 5 Java - Arquitetura de Microsserviços

## 📋 Descrição do Projeto

Este projeto implementa uma arquitetura de microsserviços para um sistema de e-commerce, desenvolvido como parte do Checkpoint 5 da FIAP. O sistema é composto por quatro microsserviços independentes que se comunicam de forma assíncrona através do RabbitMQ, seguindo os princípios de arquitetura orientada a eventos.

## 🏗️ Arquitetura

### Diagrama de Arquitetura

```
[ESPAÇO RESERVADO PARA O DIAGRAMA DE ARQUITETURA]
```

### Microsserviços

O sistema é composto pelos seguintes microsserviços:

#### 1. **Checkout Service** (`ms-checkout`)
- **Porta**: 8080
- **Responsabilidade**: Gerenciar o processo de checkout de pedidos
- **Tecnologias**: Spring Boot, Spring Web, RabbitMQ
- **Funcionalidades**:
  - Recebe solicitações de pedidos via REST API
  - Publica eventos de pedido para a fila RabbitMQ
  - Valida dados de entrada

#### 2. **Payment Service** (`ms-pagamento`)
- **Porta**: 8080
- **Responsabilidade**: Processar pagamentos
- **Tecnologias**: Spring Boot, Spring Web, RabbitMQ
- **Funcionalidades**:
  - Processa pagamentos (PIX, Cartão de Crédito)
  - Consome eventos de pedido
  - Publica eventos de pagamento confirmado
  - Gera IDs únicos para transações

#### 3. **SMS Service** (`ms-sms`)
- **Porta**: 8080
- **Responsabilidade**: Enviar notificações via SMS
- **Tecnologias**: Spring Boot, RabbitMQ, SMS Dev API
- **Funcionalidades**:
  - Consome eventos de pagamento
  - Envia notificações SMS aos clientes
  - Integração com API externa de SMS

#### 4. **Stock Service** (`ms-stock`)
- **Porta**: 8080
- **Responsabilidade**: Gerenciar estoque de produtos
- **Tecnologias**: Spring Boot, MongoDB, RabbitMQ
- **Tecnologias**: Spring Boot, MongoDB, Spring Data MongoDB, RabbitMQ
- **Funcionalidades**:
  - Consome eventos de pedido
  - Atualiza estoque de produtos
  - Persiste dados no MongoDB

## 🔄 Fluxo de Comunicação

1. **Cliente** → Envia pedido para o **Checkout Service**
2. **Checkout Service** → Publica evento de pedido no RabbitMQ
3. **Payment Service** ← Consome evento de pedido
4. **Payment Service** → Processa pagamento e publica evento de pagamento
5. **SMS Service** ← Consome evento de pagamento e envia notificação
6. **Stock Service** ← Consome evento de pedido e atualiza estoque

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.6** - Framework principal
- **Spring AMQP** - Integração com RabbitMQ
- **Spring Web** - REST APIs
- **Spring Data MongoDB** - Persistência de dados (Stock Service)
- **Lombok** - Redução de boilerplate

### Mensageria
- **RabbitMQ** - Message broker para comunicação assíncrona entre serviços

### Banco de Dados
- **MongoDB** - Banco de dados NoSQL (Stock Service)

### Build & Deploy
- **Gradle** - Ferramenta de build
- **Docker** - Containerização
- **Terraform** - Infrastructure as Code
- **Google Cloud Run** - Plataforma de deploy
- **Google Artifact Registry** - Registro de imagens Docker

### Cloud Provider
- **Google Cloud Platform (GCP)**
  - Region: `southamerica-east1` (São Paulo)
  - Project: `cp5java`

## 📦 Estrutura do Projeto

```
fiap-java-cp05/
├── microsservicos/
│   ├── checkout/           # Serviço de checkout
│   │   ├── src/
│   │   ├── build.gradle
│   │   └── Dockerfile
│   ├── pagamento/          # Serviço de pagamento
│   │   ├── src/
│   │   ├── build.gradle
│   │   └── Dockerfile
│   ├── sms/                # Serviço de notificação SMS
│   │   ├── src/
│   │   ├── build.gradle
│   │   └── Dockerfile
│   └── stock/              # Serviço de estoque
│       ├── src/
│       ├── build.gradle
│       └── Dockerfile
├── infra/                  # Infraestrutura Terraform
│   ├── main.tf
│   ├── output.tf
│   ├── terraform.tf
│   └── sa.json
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos

- Java 21 (JDK)
- Docker e Docker Compose
- Gradle (ou use o wrapper `./gradlew`)
- RabbitMQ (local ou containerizado)
- MongoDB (para o Stock Service)

### Variáveis de Ambiente

Cada microsserviço requer as seguintes variáveis de ambiente:

```bash
# RabbitMQ
RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672
RABBITMQ_USER=guest
RABBITMQ_PASSWORD=guest

# MongoDB (apenas Stock Service)
MONGODB_URI=mongodb://localhost:27017/stock

# SMS Dev API (apenas SMS Service)
SMSDEV_API_KEY=your-api-key
```

### Executando Localmente

#### 1. Subir dependências (RabbitMQ e MongoDB)

```bash
# RabbitMQ
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

# MongoDB
docker run -d --name mongodb -p 27017:27017 mongo:latest
```

#### 2. Build dos microsserviços

```bash
# Checkout Service
cd microsservicos/checkout
./gradlew clean build

# Payment Service
cd ../pagamento
./gradlew clean build

# SMS Service
cd ../sms
./gradlew clean build

# Stock Service
cd ../stock
./gradlew clean build
```

#### 3. Executar os serviços

```bash
# Em terminais separados, execute cada serviço:

# Terminal 1 - Checkout
cd microsservicos/checkout
./gradlew bootRun

# Terminal 2 - Payment
cd microsservicos/pagamento
./gradlew bootRun

# Terminal 3 - SMS
cd microsservicos/sms
./gradlew bootRun

# Terminal 4 - Stock
cd microsservicos/stock
./gradlew bootRun
```

### Executando com Docker

#### 1. Build das imagens

```bash
# Checkout Service
cd microsservicos/checkout
./gradlew clean build
docker build -t ms-checkout .

# Payment Service
cd ../pagamento
./gradlew clean build
docker build -t ms-pagamento .

# SMS Service
cd ../sms
./gradlew clean build
docker build -t ms-sms .

# Stock Service
cd ../stock
./gradlew clean build
docker build -t ms-stock .
```

#### 2. Executar containers

```bash
docker run -d -p 8081:8080 \
  -e RABBITMQ_HOST=host.docker.internal \
  -e RABBITMQ_PORT=5672 \
  -e RABBITMQ_USER=guest \
  -e RABBITMQ_PASSWORD=guest \
  ms-checkout

docker run -d -p 8082:8080 \
  -e RABBITMQ_HOST=host.docker.internal \
  -e RABBITMQ_PORT=5672 \
  -e RABBITMQ_USER=guest \
  -e RABBITMQ_PASSWORD=guest \
  ms-pagamento

docker run -d -p 8083:8080 \
  -e RABBITMQ_HOST=host.docker.internal \
  -e RABBITMQ_PORT=5672 \
  -e RABBITMQ_USER=guest \
  -e RABBITMQ_PASSWORD=guest \
  -e SMSDEV_API_KEY=your-api-key \
  ms-sms

docker run -d -p 8084:8080 \
  -e RABBITMQ_HOST=host.docker.internal \
  -e RABBITMQ_PORT=5672 \
  -e RABBITMQ_USER=guest \
  -e RABBITMQ_PASSWORD=guest \
  -e MONGODB_URI=mongodb://host.docker.internal:27017/stock \
  ms-stock
```

## ☁️ Deploy no Google Cloud

### Terraform

O projeto inclui configuração Terraform para deploy automatizado no Google Cloud Run.

#### 1. Configurar credenciais

```bash
cd infra
# Coloque seu arquivo de service account em sa.json
```

#### 2. Inicializar Terraform

```bash
terraform init
```

#### 3. Planejar deploy

```bash
terraform plan
```

#### 4. Aplicar infraestrutura

```bash
terraform apply
```

### Recursos Criados

- **4 Cloud Run Services** (um para cada microsserviço)
- **CPU**: 1 vCPU por serviço
- **Memória**: 512 Mi por serviço
- **Scaling**: Mínimo 1, Máximo 1 instância
- **Ingress**: Público (INGRESS_TRAFFIC_ALL)
- **Region**: southamerica-east1 (São Paulo)

### Secrets Manager

Os seguintes secrets são gerenciados pelo Google Secret Manager:
- `rabbitmq-host`
- `rabbitmq-user`
- `rabbitmq-password`
- `mongodb-connection-string`
- `smsdev-api-key`

## 📡 Endpoints da API

### Checkout Service

```http
POST /api/orders
Content-Type: application/json

{
  "customerId": "cliente123",
  "items": [
    {
      "sku": "PROD001",
      "quantity": 2,
      "price": 99.90
    }
  ]
}
```

### Payment Service

```http
POST /api/payments
Content-Type: application/json

{
  "orderId": "order123",
  "amount": 199.80,
  "paymentMethod": "PIX",
  "details": {
    // Detalhes específicos do método de pagamento
  }
}
```

## 🧪 Testes

```bash
# Executar testes de um serviço específico
cd microsservicos/checkout
./gradlew test

# Executar com relatório de cobertura
./gradlew test jacocoTestReport
```

## 📊 Monitoramento

- **RabbitMQ Management**: http://localhost:15672 (guest/guest)
- **Google Cloud Console**: Monitore logs e métricas dos serviços

## 🔒 Segurança

- Secrets gerenciados pelo Google Secret Manager
- IAM configurado para Cloud Run
- Comunicação entre serviços via mensageria assíncrona
- Validação de dados de entrada

## 👥 Autores

**Grupo UNICOS**

- Cauã Marcelo da Silva Machado - RM558024
- Gabriel Marques de Lima Sousa - RM554889
- Leonardo Matheus Teixeira - RM556629
- Leonardo Menezes Parpinelli Ribas - RM557908

- Repository: [fiap-java-cp05](https://github.com/unicos-tech/fiap-java-cp05)

---

**FIAP - Faculdade de Informática e Administração Paulista**
*Checkpoint 5 - Arquitetura de Microsserviços com Java e Spring Boot*